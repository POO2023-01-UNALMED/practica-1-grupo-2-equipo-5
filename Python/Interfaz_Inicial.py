from Seriliazador import Serializador
from ErrorAplicacion import *
import tkinter as tk
import regex as re
from tkinter import messagebox
from PIL import ImageTk, Image
from tabulate import tabulate
from tkinter import ttk
import tkinter.filedialog

from Celular import Celular
from FieldFrame import FieldFrame
from Cliente import Cliente
from Supermercado import Supermercado
from Libro import Libro
from Alimentos import Alimentos
import random

from tabulate import tabulate

from Tv import Tv
#from Ropa import Ropa
from Carne import Carne
from noCarnicos import noCarnicos
from Electronico import Electronico
from Deserializador import Deserializador

class Interfaz():
    def __init__(self):
        self.lista_libros = []
        self.lista_carnicos = []
        self.lista_no_carnicos = []
        self.lista_tvs = []
        self.lista_ropa = []
        self.lista_celulares = []
        self.lista_super = []
        self.cliente = Cliente()
        self.mercado = Supermercado()
        self.filtrolibro = 0

        try:
            temp = []
            temp = list(Deserializador.deserializarObjetos())
        except:
            print("No hay supermercados registrados")
        if len(temp) != 0:
            self.lista_super = temp
    def venInicio(self):

        # Ingreso a la aplicacion
        def ingresarapp():

            inicio.destroy()
            ventana_usuario = tk.Tk()
            ventana_usuario.title("Gestor T")
            ventana_usuario.geometry("1200x600")

            # Se define el frame cambiante que permanece en la zona 2
            frame_zona2 = tk.Frame(ventana_usuario)

            frame_zona2.pack()
            # Realizo la primera vista de la interfaz al ingresar a la zona 2 (Donde explico el funcionamiento de la misma)
            titulo_zona2 = tk.Label(frame_zona2, text="Bienvenidos y gracias por preferir nuestro "
                                                      "sistema gestor de tiendas y supermercados",
                                    font=("Times 13", 18), bg="lightgray")
            titulo_zona2.grid(pady=10, ipadx=15, row=0)

            texto_explicacion_zona2 = tk.Label(frame_zona2, font=("Times 13", 14))
            texto_explicacion_zona2.config(
                text="Este es un simple sistema gestor de tiendas, donde puedes buscar entre las diferentes opciones de tiendas que tenemos para ofrecerte, "
                     "\ndonde además podrás buscar entre la variedad de productos que estas nos ofrecen y puedes posteriormente realizar una compra :)")
            texto_explicacion_zona2.grid(row=1)
            pasos_uso_zona2 = tk.Label(frame_zona2, font=("Times New Roman", 14), bg="lightblue")
            pasos_uso_zona2.config(
                text="- Lo primero que debes hacer es notar el menu de navegacion de la aplicacion, donde deberás entrar al apartado de 'Procesos y Consultas'"
                     "\n\n\n- Posteriormente, debes dirigirte al apartado 'Identificar usuario', donde deberas ingresar los datos que se te piden y dar en el boton 'Aceptar'"
                     "\n\n\n- Luego deberas elegir si deseas crear un supermercado o crearlo, por lo que en el menu 'Procesos y Consultas' encontraras dichas opciones"
                     "\n\n\n- En general, encontraras todo lo que nececitas en el apartado 'Procesos y Consultas'. Debes tener cuidado de seguir bien el flujo del programa"
                     "\n\n\n- Al finalizar todas tus compras (libros, comida, componentes electronicos, Ropa) debes dirigirte al carrito de compra y concretar tu pedido"
                     "\n\n\n\n ¡Espero que disfrutes usando nuestro gestor de tiendas!")
            pasos_uso_zona2.grid(row=2, pady=30, ipadx=10, ipady=20)

            # Función para añadir productos al carrito

            def añadirAlCarrito(producto, ofermerca):
                prodencarrito = False

                for p in self.cliente.carrito:
                    if p.nombre == producto.nombre:
                        prodencarrito = True
                        p.cantidad = int(p.cantidad) + 1
                        break

                if not prodencarrito:

                    if isinstance(producto, Libro):
                        self.cliente.carrito.append(Libro(producto, 1))
                    elif isinstance(producto, Electronico):
                        self.cliente.carrito.append(Electronico(producto, 1))
                    elif isinstance(producto, Alimentos):
                        self.cliente.carrito.append(Alimentos(producto, 1))

                producto.cantidad = int(producto.cantidad) - 1
                if producto.cantidad == 0:
                    ofermerca.remove(producto)

            # Funcion para limpiar el frame
            def limpia_frame():
                for widget in frame_zona2.winfo_children():
                    widget.destroy()

            # Se definen las funciones de los Widgets

            # Mensaje del boton acerca de
            def acercade():
                texto_acerca_de = "Autores:\n\n-Alejandro Ramírez Ramírez\n\n-Rubén Urias Salas\n\n-Santiago Acevedo Cacua\n\n-Yiduar Duvier Rangel Quintero "
                messagebox.showinfo("Acerca de :", texto_acerca_de)

            # Mensaje del boton aplicacion    
            def menBapli():
                mensaje_bapli = """Esta aplicación le ayuda a gestionar sus supemermercados.\n
    También perminte a los clientes comprar en el supermercado de su preferencia."""
                messagebox.showinfo("Aplicacion", mensaje_bapli)

            # Boton salir de la ventan de usuario
            def salirapp():
                ventana_usuario.destroy()
                Interfaz.venInicio(self)

            # ----------------- Funcionalidades y Procesos --------------

            # Boton ver carrito de compras
            def finalizarCompra():
                limpia_frame()

                tk.Label(frame_zona2, text=f"BIENVENIDO AL CARRITO DE COMPRAS"
                                           f"\n***MUCHAS GRACIAS POR COMPRAR CON NOSOTROS A CONTINUACION SU CARRITO DE COMPRAS***",
                         borderwidth=2, relief="solid", font="Times 13",
                         bg="white").grid(row=0, columnspan=1, pady=8)

                # Cargar imagen 
                imagenCarne = Image.open("CarritoComprass.jpg")
                # Redimensionar la imagen si es necesario
                imagenCarne = imagenCarne.resize((125, 125))
                # Crear objeto PhotoImage y mantener una referencia
                imagenCarne = ImageTk.PhotoImage(imagenCarne)

                # Crear el widget Label para mostrar la imagen
                # carne_label = tk.Label(frame_zona2, image=imagenCarne, relief="solid")
                # carne_label.image = imagenCarne
                # carne_label.grid(row=1, column=0)

                encabezados = ["Nombre", "Tipo", "Supermercado", "Cantidad", "Precio", "Total"]
                productos = []
                total = 0
                for p in self.cliente.carrito:
                    if isinstance(p, Libro):
                        productos.append([p.nombre, "Libro", p.supermercado.nombre, str(p.cantidad),
                                          str(int(p.cantidad) * int(p.precio)), ""])
                    elif isinstance(p, Alimentos):
                        productos.append([p.nombre, "Alimento", p.supermercado.nombre, str(p.cantidad),
                                          str(int(p.cantidad) * int(p.precio)), ""])
                    elif isinstance(p, Electronico):
                        productos.append(
                            [p.marca + ": " + p.nombre, "Electronico", p.supermercado.nombre, str(p.cantidad),
                             str(int(p.cantidad) * int(p.precio)), ""])

                    total = total + (int(p.cantidad) * int(p.precio))

                productos.append(["", "", "", "", "", str(total)])

                tabla = tabulate(productos, headers=encabezados, tablefmt="fancy_grid")

                text_widget = tk.Text(frame_zona2, font=("Courier", 10), relief="solid")
                text_widget.tag_configure("center", justify="center")
                text_widget.insert(tk.END, tabla, "center")
                text_widget.grid(row=1, column=0)

                # Funciones de los botones
                def pagar():

                    if self.cliente.saldo < total:
                        raise saldoInsuficiente
                    else:
                        mensaje_salida = f"""¡Muchas gracias por su compra!\nSu pedido será enviado a nombre de 
                        {self.cliente.nombre} con direccion a {self.cliente.direccion}.\n\n
                        ¡Esperamos que vuelva pronto!"""
                        messagebox.showinfo("Pago Aplicación", mensaje_salida)
                        ventana_usuario.destroy()

                def quitarProds():
                    limpia_frame()

                    def devolverProd(event):
                        selected_item = self.cliente.carrito[int(listbox.get(listbox.curselection())[0]) - 1]

                        limpia_frame()

                        def aceptar():

                            devolverField.valores = [x.get() for x in devolverField.lst_entrys]

                            if int(devolverField.valores[0]) > int(selected_item.cantidad):
                                raise cantidadMaxima(selected_item.cantidad)

                            if int(selected_item.cantidad) - int(devolverField.valores[0]) == 0:
                                self.cliente.carrito.remove(selected_item)
                            else:
                                selected_item.cantidad = int(selected_item.cantidad) - int(devolverField.valores[0])

                            messagebox.showinfo("Regresar productos", "¡Producto devuelto con éxito!")

                        tk.Label(frame_zona2, text="Quitar productos", borderwidth=2, relief="solid", font="Times 13",
                                 bg="white").pack(pady=20)

                        tk.Label(frame_zona2, text="Ingrese las unidades del producto que quiere devolver",
                                 borderwidth=2, relief="solid", font="Times 13",
                                 bg="white").pack(pady=20)

                        devolverField = FieldFrame(frame_zona2, "Devolver", ["Cantidad a devolver"], "Cantidad", None,
                                                   None)
                        devolverField.pack(pady=10)

                        Aceptar = tk.Button(devolverField, text="Aceptar", font="Times 13", command=aceptar)
                        Aceptar.grid(row=len(devolverField.criterios) + 1, column=0, pady=10)

                    tk.Label(frame_zona2, text="Quitar", borderwidth=2, relief="solid", font="Times 13",
                             bg="white").pack(pady=20)

                    tk.Label(frame_zona2, text="Seleccione el producto que desea quitar del carrito", borderwidth=2,
                             relief="solid", font="Times 13",
                             bg="white").pack(pady=20)

                    listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13", bg="white")
                    listbox.pack(pady=20)
                    for s in range(len(self.cliente.carrito)):
                        listbox.insert(tk.END, str(s + 1) + ". " + self.cliente.carrito[s].nombre)

                    listbox.bind('<<ListboxSelect>>', devolverProd)

                tk.Button(frame_zona2, text="Pagar", font="Times 13", command=pagar).grid(row=2, column=0, pady=10)
                tk.Button(frame_zona2, text="Quitar Productos", font="Times 13", command=quitarProds).grid(row=3,
                                                                                                           column=0,
                                                                                                           pady=10)

                # Completar------------------

            # boton identificar usuario
            def IdenUsuario():
                limpia_frame()

                tk.Label(frame_zona2, text="Identificar Usuario", borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)
                descrip_idenusuario = """Este proceso permite la identificación del usuario.\nEsto se hace por medio de sus datos personales básicos"""
                tk.Label(frame_zona2, text=descrip_idenusuario, borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)

                criUser = ["Nombre", "Dirección", "Saldo"]

                identificadores = FieldFrame(frame_zona2, "Datos", criUser, "Valor", None, None)
                identificadores.pack()

                # Acciones al oprimir el boton aceptar
                def aceptar():
                    identificadores.valores = [x.get() for x in identificadores.lst_entrys]
                    if "" in identificadores.valores:
                        raise entrySinvalor
                    else:
                        try:
                            if re.match(r"\D", identificadores.valores[0]) == None:
                                buentipo = [criUser[0], "palabras/letras"]  # Parametro nombre
                                0 / 0
                            if re.match(r"\D", identificadores.valores[1]) == None:
                                buentipo = [criUser[1], "palabras/letras"]  # Parametro direccion
                                0 / 0
                            buentipo = [criUser[2], "un numero"]  # Parametro saldo
                            int(identificadores.valores[2])

                            self.cliente.nombre = identificadores.getValue("Nombre")
                            self.cliente.direccion = identificadores.getValue("Dirección")
                            self.cliente.saldo = identificadores.getValue("Saldo")

                            otro = messagebox.showinfo(
                                message="¡Usuario registrado con éxito!",
                                title="Usuario")

                            if otro:
                                ofertaProductos()
                        except:
                            raise dtipoInvalido(buentipo[0], buentipo[1])

                Aceptar = tk.Button(identificadores, text="Aceptar", font="Times 13", command=aceptar)
                Aceptar.grid(row=len(identificadores.criterios) + 1, column=0, pady=10)

            # Boton crear supermercado

            def crearsuper():
                limpia_frame()

                def crearmercado():
                    # Obtiene los valores del frame anterior
                    if c_super.valores is None:
                        c_super.valores = [x.get() for x in c_super.lst_entrys]
                    self.mercado = Supermercado()
                    self.mercado.nombre = c_super.getValue("Nombre")
                    self.lista_super.append(self.mercado)
                    agregarprods()

                tk.Label(frame_zona2, text="Crear Supermercado", borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)
                descrip_crearsup = "Este proceso le permite crear un nuevo supermercado y agregar los productos que necesita"
                tk.Label(frame_zona2, text=descrip_crearsup, borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)
                c_super = FieldFrame(frame_zona2, "Nuevo Supermercado", ["Nombre"],
                                     "Ingrese el nombre del nuevo supermercado", None, None)
                c_super.pack()

                Aceptar = tk.Button(c_super, text="Aceptar", font="Times 13", command=crearmercado)
                Aceptar.grid(row=len(c_super.criterios) + 1, column=0, pady=10)

            def agregarprods():

                def agregarRopa():
                    limpia_frame()
                tk.Label(frame_zona2, text=f"Agregar Ropa al Supermercado {self.mercado.nombre}", borderwidth=2,
                         relief="solid", font="Times 13", bg="white").pack(pady=20)
                tk.Label(frame_zona2, text="Aquí podrás agregar productos de ropa al supermercado seleccionado",
                         borderwidth=2, relief="solid", font="Times 13", bg="white").pack(pady=20)
                criterios_ropa = ["Tipo", "Marca", "Talla", "Color", "Precio", "Cantidad"]
                ropafield = FieldFrame(frame_zona2, "Datos del producto de ropa", criterios_ropa, "Valores", None, None)
                ropafield.pack()
                
                #Boton Aceptar #Yiduar
                """def agregarRopa():
                    ropafield.valores = [x.get() for x in ropafield.lst_entrys]
                    if "" in ropafield.valores:
                      raise entrySinvalor
                    else:
                        try:
                            tipo = ropafield.valores[0]
                            marca = ropafield.valores[1]
                            talla = ropafield.valores[2]
                            color = ropafield.valores[3]
                            precio = float(ropafield.valores[4])
                            cantidad = int(ropafield.valores[5])

                            ruta_imagen = tkinter.filedialog.askopenfilename()
                        
                            ropa = Ropa(tipo, marca, talla, color, precio, cantidad, self.mercado,ruta_imagen)
                            self.mercado.agregar_producto(ropa)
                        
                            otro = messagebox.askyesno(
                                message="¡Producto agregado con éxito!\n\n¿Desea agregar otro producto de ropa?",
                                title="Producto")
                            if otro:
                                agregarRopa()
                            else:
                                crearsuper()
                        except ValueError:
                            messagebox.showerror("Error", "El precio debe ser un número válido.")

                aceptar = tk.Button(ropafield, text="Aceptar", font="Times 13", command=agregarRopa)
                aceptar.grid(row=len(ropafield.criterios) + 1, column=0, pady=10)"""

                def agregarLibro():
                    limpia_frame()

                    tk.Label(frame_zona2, text=f"Agregar Libros a {self.mercado.nombre}", borderwidth=2,
                             relief="solid", font="Times 13", bg="white").pack(pady=20)
                    tk.Label(frame_zona2, text="Aquí podrá agregar libros al supermercado seleccionado",
                             borderwidth=2, relief="solid", font="Times 13", bg="white").pack(pady=20)
                    crilibros = ["Titulo", "Autor", "Descripcion", "ISBN", "Precio", "Cantidad"]
                    bookfield = FieldFrame(frame_zona2, "Datos del libro", crilibros, "Valores", None, None)
                    bookfield.pack()

                    # Boton Aceptar
                    def agregarLib():
                        bookfield.valores = [x.get() for x in bookfield.lst_entrys]

                        if "" in bookfield.valores:
                            raise entrySinvalor
                        else:

                            try:
                                if re.match(r"\D", bookfield.valores[0]) == None:
                                    buentipo = [crilibros[0], "palabras/letras"]
                                    raise Exception
                                if re.match(r"\D", bookfield.valores[1]) == None:
                                    buentipo = [crilibros[1], "palabras/letras"]
                                    raise Exception
                                if re.match(r"\D", bookfield.valores[2]) == None:
                                    buentipo = [crilibros[2], "palabras/letras"]
                                    raise Exception
                                buentipo = [crilibros[3], "un numero"]
                                int(bookfield.valores[3])
                                buentipo = [crilibros[4], "un numero"]
                                int(bookfield.valores[4])
                                buentipo = [crilibros[5], "un numero"]
                                int(bookfield.valores[5])

                                libro = Libro(bookfield.valores[0], bookfield.valores[1], bookfield.valores[2]
                                              , bookfield.valores[3], bookfield.valores[4], bookfield.valores[5]
                                              , self.mercado)

                                self.mercado.oferlibros.append(libro)

                                otro = messagebox.askyesno(
                                    message="¡Producto agregado con éxito!\n\n¿Desea agregar un producto diferente?",
                                    title="Producto")

                                if otro:
                                    agregarprods()

                            except:
                                raise dtipoInvalido(buentipo[0], buentipo[1])

                    Aceptar = tk.Button(bookfield, text="Aceptar", font="Times 13", command=agregarLib)
                    Aceptar.grid(row=len(bookfield.criterios) + 1, column=0, pady=10)

                limpia_frame()

                tk.Label(frame_zona2, text="Agregar Productos", borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)
                tk.Label(frame_zona2, text=f"¿Que productos deseas agregar al Supermercado {self.mercado.nombre}",
                         borderwidth=2,
                         relief="solid", font="Times 13", bg="white").pack(pady=20)

                # Botones para agregar diferentes tipos de producto
                tk.Button(frame_zona2, text="Libros", font="Times 13", command=agregarLibro).pack(pady=10)
                tk.Button(frame_zona2, text="Electronica", font="Times 13", command=agregarElectronica).pack(
                    pady=10)
                tk.Button(frame_zona2, text="Alimentos", font="Times 13", command=AgregarAlimentos).pack(pady=10)

            # Flujo para agregar Alimentos
            # Funcionalidad Alimentos......................  
            def agregarCarnico():
                limpia_frame()

                tk.Label(frame_zona2, text=f"Agregar Alimentos Carnicos al Supermercado {self.mercado.nombre}",
                         borderwidth=2,
                         relief="solid", font="Times 13", bg="white").pack(pady=20)
                tk.Label(frame_zona2, text="Aquí podrá agregar Alimentos Carnicos al Supermercado seleccionado",
                         borderwidth=2, relief="solid", font="Times 13", bg="white").pack(pady=20)
                cricarne = ["Nombre", "Precio por Libra", "Tipo de Producto", "Peso en Libras",
                            "Unidades que desea añadir"]
                carnefield = FieldFrame(frame_zona2, "Datos del producto Carncio", cricarne, "Valores", None, None)
                carnefield.pack()

                # Boton Aceptar
                def agregarCarne():
                    carnefield.valores = [x.get() for x in carnefield.lst_entrys]
                    if "" in carnefield.valores:
                        raise entrySinvalor
                    else:
                        try:
                            if re.match(r"\D", carnefield.valores[0]) == None:
                                buentipo = [cricarne[0], "palabras/letras"]  # Parametro nombre
                                0 / 0
                            buentipo = [cricarne[1], "un numero"]  # paremetro precio
                            int(carnefield.valores[1])
                            if re.match(r"\D", carnefield.valores[2]) == None:
                                buentipo = [cricarne[2], "palabras/letras"]  # Parametro tipo
                                0 / 0
                            buentipo = [cricarne[3], "un numero"]  # Parametro peso libra
                            int(carnefield.valores[3])
                            buentipo = [cricarne[4], "un numero"]  # Parametro cantidad
                            int(carnefield.valores[4])

                            carne = Carne(carnefield.valores[0], carnefield.valores[1], carnefield.valores[2]
                                          , carnefield.valores[3], carnefield.valores[4], self.mercado)

                            self.mercado.ofercarne.append(carne)

                            otro = messagebox.askyesno(
                                message="¡Producto agregado con éxito!\n\n¿Desea agregar un producto diferente?",
                                title="Producto")

                            if otro:
                                agregarprods()
                            else:
                                crearsuper()
                        except:
                            raise dtipoInvalido(buentipo[0], buentipo[1])

                aceptar = tk.Button(carnefield, text="Aceptar", font="Times 13", command=agregarCarne)
                aceptar.grid(row=len(carnefield.criterios) + 1, column=0, pady=10)

            def agregarnoCarnicos():
                limpia_frame()

                tk.Label(frame_zona2, text=f"Agregar Alimentos noCarnicos al Supermercado {self.mercado.nombre}",
                         borderwidth=2,
                         relief="solid", font="Times 13", bg="white").pack(pady=20)
                tk.Label(frame_zona2, text="Aquí podrá agregar Alimentos noCarnicos al Supermercado seleccionado",
                         borderwidth=2, relief="solid", font="Times 13", bg="white").pack(pady=20)

                crinocarnes = ["Nombre", "Precio", "Categoria:1.Granos / 2.Lacteos / 3.Verduras / 4.Otros",
                               "Cantidad unidades que desea añadir"]
                nocarnefield = FieldFrame(frame_zona2, "Datos del producto noCarncio", crinocarnes, "Valores", None,
                                          None)
                nocarnefield.pack()

                # Boton Aceptar
                def agregarnoCarne():
                    nocarnefield.valores = [x.get() for x in nocarnefield.lst_entrys]

                    if "" in nocarnefield.valores:
                        raise entrySinvalor
                    else:
                        try:
                            if re.match(r"\D", nocarnefield.valores[0]) == None:
                                buentipo = [crinocarnes[0], "palabras/letras"]  # Parametro nombre
                                0 / 0
                            buentipo = [crinocarnes[1], "un numero"]  # paremetro precio
                            int(nocarnefield.valores[1])
                            buentipo = [crinocarnes[2], "un numero"]  # Parametro grupo
                            int(nocarnefield.valores[2])
                            buentipo = [crinocarnes[3], "un numero"]  # Parametro cantidad
                            int(nocarnefield.valores[3])

                            nocarne = noCarnicos(nocarnefield.valores[0], nocarnefield.valores[1],
                                                 nocarnefield.valores[2]
                                                 , nocarnefield.valores[3], self.mercado)
                            self.mercado.ofernocarnicos.append(nocarne)

                            otro = messagebox.askyesno(
                                message="¡Producto agregado con éxito!\n\n¿Desea agregar un producto diferente?",
                                title="Producto")

                            if otro:
                                agregarprods()
                            else:
                                crearsuper()
                        except:
                            raise dtipoInvalido(buentipo[0], buentipo[1])

                aceptar = tk.Button(nocarnefield, text="Aceptar", font="Times 13", command=agregarnoCarne)
                aceptar.grid(row=len(nocarnefield.criterios) + 1, column=0, pady=10)

            def AgregarAlimentos():
                limpia_frame()
                tk.Label(frame_zona2,
                         text=f"Binevenido al area de Alimentos del Supermercado {self.mercado.nombre}?",
                         borderwidth=3, relief="solid", font="Times 13", bg="white").grid(row=0, columnspan=2,
                                                                                          pady=35)
                tk.Label(frame_zona2,
                         text=f"¿Que tipo de Alimentos deseas agregar al Supermercado {self.mercado.nombre}?",
                         borderwidth=3, relief="solid", font="Times 13", bg="white").grid(row=3, columnspan=2,
                                                                                          pady=35)

                # Cargar imagen Carnicos
                imagenCarne = Image.open("Carnes.jpg")
                # Redimensionar la imagen si es necesario
                imagenCarne = imagenCarne.resize((200, 200))
                # Crear objeto PhotoImage y mantener una referencia
                imagenCarne = ImageTk.PhotoImage(imagenCarne)

                # Crear el widget Label para mostrar la imagen de Carnicos
                carne_label = tk.Label(frame_zona2, image=imagenCarne, relief="solid")
                carne_label.image = imagenCarne
                carne_label.grid(row=1, column=0)

                tk.Button(frame_zona2, text="Carnicos", font="Times 13", command=agregarCarnico).grid(row=2,
                                                                                                      column=0,
                                                                                                      pady=20)

                # Cargar la imagen de noCarnicos
                imagennoCarne = Image.open("noCarnico.jpg")
                # Redimensionar la imagen si es necesario
                imagennoCarne = imagennoCarne.resize((200, 200))
                # Crear objeto PhotoImage y mantener una referencia
                imagennoCarne = ImageTk.PhotoImage(imagennoCarne)

                # Crear el widget Label para mostrar la imagen noCarnicos
                nocarne_label = tk.Label(frame_zona2, image=imagennoCarne, relief="solid")
                nocarne_label.image = imagennoCarne
                nocarne_label.grid(row=1, column=1)

                tk.Button(frame_zona2, text="noCarnico", font="Times 13", command=agregarnoCarnicos).grid(row=2,
                                                                                                          column=1,
                                                                                                          pady=20)

            # Procesos alimentos......................
            def MenuAlimentos():
                if self.cliente.nombre == None:
                    raise comprarSinUsuario(IdenUsuario)
                limpia_frame()

                tk.Label(frame_zona2,
                         text=f"Binevenido a la seccion de Alimentos del Supermercado {self.mercado.nombre}?",
                         borderwidth=3, relief="solid", font="Times 13", bg="white").grid(row=0, columnspan=2,
                                                                                          pady=10)
                tk.Label(frame_zona2,
                         text=f"¿En que producto esta interesado de nuestra sección?",
                         borderwidth=3, relief="solid", font="Times 13", bg="white").grid(row=1, columnspan=2,
                                                                                          pady=10)

                # Cargar imagen Carnicos
                opcCarne = Image.open("Food.jpg")
                # Redimensionar la imagen si es necesario
                opcCarne = opcCarne.resize((180, 180))
                # Crear objeto PhotoImage y mantener una referencia
                opcCarne = ImageTk.PhotoImage(opcCarne)

                # Crear el widget Label para mostrar la imagen de Carnicos
                Opcarne_label = tk.Label(frame_zona2, image=opcCarne, relief="solid")
                Opcarne_label.image = opcCarne
                Opcarne_label.grid(row=2, column=0)

                tk.Button(frame_zona2, text="!COMPRAR SOLO ALIMENTOS CARNICOS", font="Times 13",
                          command=lambda: ofertaCarnicos(self.mercado.ofercarne, 1)).grid(row=3,
                                                                                          column=0,
                                                                                          pady=10)

                tk.Button(frame_zona2, text="!COMPRAR SOLO ALIMENTOS noCARNICOS", font="Times 13",
                          command=lambda: ofertaCarnicos(self.mercado.ofernocarnicos, 2)).grid(row=4,
                                                                                               column=0,
                                                                                               pady=15)

                tk.Button(frame_zona2, text="!COMPRAR ALIMENTOS CARNICOS Y noCARNICOS", font="Times 13",
                          command=lambda: ofertaCarnicos(self.mercado.ofernocarnicos + self.mercado.ofercarne, 3)).grid(
                    row=5,
                    column=0,
                    pady=15)

            def ofertaCarnicos(listaAlimento, opcion):
                limpia_frame()

                if (len(listaAlimento) == 0):
                    tk.Label(frame_zona2, text="Esta seccion no tiene productos \n\n:(\n\n¿Desea ingresar un producto?",
                             borderwidth=2, relief="solid", font="Times 14", bg="white").grid(pady=30, ipadx=16,
                                                                                              ipady=15, columnspan=4)

                    if opcion == 1:
                        tk.Button(frame_zona2, text="Si", font=("Times 13", 16), command=agregarCarnico).grid(row=1,
                                                                                                              column=2,
                                                                                                              pady=20)
                    elif opcion == 2:
                        tk.Button(frame_zona2, text="Si", font=("Times 13", 16), command=agregarnoCarnicos).grid(row=1,
                                                                                                                 column=2,
                                                                                                                 pady=20)
                    elif opcion == 3:
                        tk.Button(frame_zona2, text="Si, Carnicos", font=("Times 13", 16),
                                  command=agregarnoCarnicos).grid(row=1,
                                                                  column=2,
                                                                  pady=20)

                        tk.Button(frame_zona2, text="Si, noCarnicos", font=("Times 13", 16),
                                  command=agregarnoCarnicos).grid(row=2,
                                                                  column=2,
                                                                  pady=20)

                    tk.Button(frame_zona2, text="No, ir a otra Sección", font=("Times 13", 16),
                              command=ofertaProductos).grid(row=3, column=2, pady=20)
                else:
                    # funcion de los botones del listbox
                    def listboxselectCarne(event):

                        selected_item = listaAlimento[int(listbox.get(listbox.curselection())[0]) - 1]

                        if opcion == 1:
                            tk.Label(frame_zona2, text=ComprarAlimento(selected_item, 1), borderwidth=2, relief="solid",
                                     font="Times 13",
                                     bg="white").grid(pady=20)
                        elif opcion == 2:
                            tk.Label(frame_zona2, text=ComprarAlimento(selected_item, 2), borderwidth=2, relief="solid",
                                     font="Times 13",
                                     bg="white").grid(pady=20)
                        elif opcion == 3:
                            tk.Label(frame_zona2, text=ComprarAlimento(selected_item, 3), borderwidth=2, relief="solid",
                                     font="Times 13",
                                     bg="white").grid(pady=20)

                    # Presentación de bienvenida
                    if opcion == 1:  # Oferta carnicos
                        tk.Label(frame_zona2,
                                 text="Bienvenido al Area de Carnes del Supermercado\n\n*** !CARNES EN OFERTA!***\n\nAcontinuacion nuestros productos disponibles",
                                 borderwidth=2, relief="solid", font="Times 14", bg="white").grid(pady=20, ipadx=5,
                                                                                                  ipady=5,
                                                                                                  columnspan=5, row=0)
                    elif opcion == 2:  # oferta nocarnicos
                        tk.Label(frame_zona2,
                                 text="Bienvenido al Area de noCarnes del Supermercado\n\n*** !noCARNICOS EN OFERTA!***\n\nAcontinuacion nuestros productos disponibles",
                                 borderwidth=2, relief="solid", font="Times 14", bg="white").grid(pady=20, ipadx=5,
                                                                                                  ipady=5,
                                                                                                  columnspan=5, row=0)
                    elif opcion == 3:  # Oferta carnicos y nocarnicos
                        tk.Label(frame_zona2,
                                 text="Bienvenido al Area de Alimentos del Supermercado\n\n*** !ACÁ TODOS LOS ALIMENTOS EN OFERTA!***\n\nAcontinuacion nuestros productos disponibles",
                                 borderwidth=2, relief="solid", font="Times 14", bg="white").grid(pady=20, ipadx=5,
                                                                                                  ipady=5,
                                                                                                  columnspan=5, row=0)

                    # Se crea el listbox para mostrar los productos
                    listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13", bg="white")

                    listbox.grid(row=1, column=2)

                    if not listaAlimento == []:
                        for l in range(len(listaAlimento)):
                            listbox.insert(tk.END, str(l + 1) + ". " + listaAlimento[l].nombre)

                    listbox.grid()

                    listbox.bind('<<ListboxSelect>>', listboxselectCarne)

                    # Se muestra las opciones adicionales de filtros e ir a otras secciones
                    tk.Label(frame_zona2, text="***Opciones adcionales***",
                             borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2,
                                                                                              row=2, column=2)
                    if opcion == 1:
                        tipoCarne = Carne.listaTipos(self.mercado.ofercarne)
                        tk.Button(frame_zona2, text="Filtrar por tipo de carne", font=("Times 12", 10),
                                  command=lambda: filtrosAliementos(tipoCarne, 1)).grid(row=3, column=2, pady=8)
                    elif opcion == 2:
                        Grupo = ['Granos', 'Lácteos', 'Vegetales', 'Otros']
                        tk.Button(frame_zona2, text="Filtrar por Categoria", font=("Times 12", 10),
                                  command=lambda: filtrosAliementos(Grupo, 2)).grid(row=3, column=2, pady=8)

                    tk.Button(frame_zona2, text="Volver al menu Alimentos", font=("Times 12", 10),
                              command=MenuAlimentos).grid(row=4, column=2, pady=8)
                    tk.Button(frame_zona2, text="Escoger otra Seccion", font=("Times 12", 10),
                              command=ofertaProductos).grid(row=5, column=2, pady=8)

            def filtrosAliementos(lista_filtro, fil_Alim):
                limpia_frame()

                if fil_Alim == 1:  # filtro por tipo de carne
                    tk.Label(frame_zona2, text=f"***Bienvenido al filtro por Tipo de Carne***"
                                               f"\nEstas son las Carnes disponibles\nSeleccione el tipo de carne que desea filtrar",
                             borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2,
                                                                                              row=1, column=2)

                elif fil_Alim == 2:  # filtro por tipo de nocarnicos
                    tk.Label(frame_zona2, text=f"***Bienvenido al filtro por Categoria de noCarnicos***"
                                               f"\nEstas son las Carnes disponibles\nSeleccione el tipo de carne que desea filtrar",
                             borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2,
                                                                                              row=1, column=2)

                # Se define el metodo para mostrar la listabox
                def listbox_filtroCarne(event):

                    selected_item = lista_filtro[int(listbox_f.get(listbox_f.curselection())[0]) - 1]

                    # Comprar el alimento escogido
                    tk.Label(frame_zona2, text=mostrar_filtro(selected_item), borderwidth=2, relief="solid",
                             font="Times 13",
                             bg="white").grid(pady=20)

                # Se crea el listbox para mostrar los productos
                listbox_f = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13", bg="white")

                listbox_f.grid(row=2, column=2)

                if not lista_filtro == []:
                    for k in range(len(lista_filtro)):
                        listbox_f.insert(tk.END, str(k + 1) + ". " + lista_filtro[k])

                listbox_f.grid()

                listbox_f.bind('<<ListboxSelect>>', listbox_filtroCarne)

                if fil_Alim == 1:
                    def mostrar_filtro(selected_item):

                        def lista_filtro(event):

                            selected_item2 = self.mercado.ofercarne[int(listbox_f.get(listbox_f.curselection())[0]) - 1]

                            # Comprar el alimento escogido
                            tk.Label(frame_zona2, text=ComprarAlimento(selected_item2, 1), borderwidth=2,
                                     relief="solid", font="Times 13",
                                     bg="white").grid(pady=20)

                        # Se crea el listbox para mostrar los productos
                        listbox_f = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13", bg="white")
                        listbox_f.grid(row=5, column=2)

                        if not self.mercado.ofercarne == []:
                            for j in range(len(self.mercado.ofercarne)):
                                if self.mercado.ofercarne[j].tipo == selected_item:
                                    listbox_f.insert(tk.END, str(j + 1) + ". " + self.mercado.ofercarne[j].nombre)

                        listbox_f.grid()

                        listbox_f.bind('<<ListboxSelect>>', lista_filtro)

                        tk.Label(frame_zona2,
                                 text=f"**Escoja la oferta que desee comprar**\nEl resultado para la carne {selected_item} es",
                                 borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8,
                                                                                                  ipady=2, row=4,
                                                                                                  column=2)
                elif fil_Alim == 2:
                    def mostrar_filtro(selected_item):

                        def lista_filtro(event):

                            selected_item2 = self.mercado.ofernocarnicos[
                                int(listbox_f.get(listbox_f.curselection())[0]) - 1]

                            # Comprar el alimento escogido
                            tk.Label(frame_zona2, text=ComprarAlimento(selected_item2, 2), borderwidth=2,
                                     relief="solid", font="Times 13",
                                     bg="white").grid(pady=20)

                        # Se crea el listbox para mostrar los productos
                        listbox_f = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13", bg="white")
                        listbox_f.grid(row=5, column=2)

                        dic_filtro = {"Granos": "1", "Lácteos": "2", "Vegetales": "3", "Otros": "4"}

                        if not self.mercado.ofernocarnicos == []:
                            for j in range(len(self.mercado.ofernocarnicos)):
                                if self.mercado.ofernocarnicos[j].grupo == dic_filtro[selected_item]:
                                    listbox_f.insert(tk.END, str(j + 1) + ". " + self.mercado.ofernocarnicos[j].nombre)

                        listbox_f.grid()
                        listbox_f.bind('<<ListboxSelect>>', lista_filtro)

                        tk.Label(frame_zona2,
                                 text=f"****Escoja la oferta que desee comprar****\nEl resultado para los prodcutos {selected_item} es",
                                 borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8,
                                                                                                  ipady=2, row=4,
                                                                                                  column=2)

            def ComprarAlimento(objeto, eleccion):
                limpia_frame()
                dic_filtro = {"1": "Granos", "2": "Lácteos", "3": "Vegetales",
                              "4": "Otros"}  # Dicionario de compra noCarnicos

                tk.Label(frame_zona2, text="SECCIÓN DE COMPRAS DE ALIMENTOS",
                         borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2,
                                                                                          columnspan=5)
                tk.Label(frame_zona2, text=f"Has seleccionado la Oferta {objeto.nombre}",
                         borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2,
                                                                                          columnspan=5)
                if eleccion == 1:  # comprar Carne
                    tk.Label(frame_zona2,
                             text=f"Nombre: {objeto.nombre}\nPrecio por libra: {objeto.precio}\nTipo de carne: {objeto.tipo}"
                                  f"\nLibras por unidad: {objeto.pesolibra}\nUnidades en Stock: {objeto.cantidad}",
                             borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2,
                                                                                              columnspan=5)

                elif eleccion == 2:  # comprar noCarnico
                    tk.Label(frame_zona2,
                             text=f"Nombre: {objeto.nombre}\nPrecio por unidad: {objeto.precio}\nTipo de categoria: {dic_filtro[objeto.grupo]}"
                                  f"\nUnidades en Stock: {objeto.cantidad}",
                             borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2,
                                                                                              columnspan=5)

                elif eleccion == 3:  # comprar carnico y nocarnicos

                    if (objeto in self.mercado.ofercarne):
                        tk.Label(frame_zona2,
                                 text=f"Nombre: {objeto.nombre}\nPrecio por libra: {objeto.precio}\nTipo de carne: {objeto.tipo}"
                                      f"\nLibras por unidad: {objeto.pesolibra}\nUnidades en Stock: {objeto.cantidad}",
                                 borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8,
                                                                                                  ipady=2,
                                                                                                  columnspan=5)
                    elif (objeto in self.mercado.ofernocarnicos):
                        tk.Label(frame_zona2,
                                 text=f"Nombre: {objeto.nombre}\nPrecio por unidad: {objeto.precio}\nTipo de categoria: {dic_filtro[objeto.grupo]}"
                                      f"\nUnidades en Stock: {objeto.cantidad}",
                                 borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8,
                                                                                                  ipady=2,
                                                                                                  columnspan=5)

                def carrito():
                    self.cliente.carrito.append(objeto)
                    otro = messagebox.askyesno(
                        message="¡Producto agregado con éxito!\n\n¿Desea finalizar su compra?", title="Alimentos")

                    if otro:
                        finalizarCompra()
                        pass
                    else:
                        ComprarAlimento(objeto, eleccion)

                tk.Button(frame_zona2, text="Agregar al carrito de compras", font=("Times 12", 10),
                          command=carrito).grid(row=3, column=2, pady=10)

                tk.Button(frame_zona2, text="Escoger otra Seccion", font=("Times 12", 10),
                          command=ofertaProductos).grid(row=4, column=2, pady=10)

                if eleccion == 1:
                    tk.Button(frame_zona2, text="Volver", font=("Times 12", 10),
                              command=lambda: ofertaCarnicos(self.mercado.ofercarne, 1)).grid(row=5,
                                                                                              column=2,
                                                                                              pady=10)
                elif eleccion == 2:
                    tk.Button(frame_zona2, text="Volver", font=("Times 12", 10),
                              command=lambda: ofertaCarnicos(self.mercado.ofernocarnicos, 2)).grid(row=5,
                                                                                                   column=2,
                                                                                                   pady=10)
                elif eleccion == 3:
                    tk.Button(frame_zona2, text="Volver", font=("Times 12", 10),
                              command=lambda: ofertaCarnicos(self.mercado.ofernocarnicos + self.mercado.ofercarne,
                                                             3)).grid(row=5,
                                                                      column=2,
                                                                      pady=10)

                    # PARTE DE LOS ELECTRONICOS -----------------------------------------
            #Apartado para ver información del celular seleccionado y determinar compra
            def comprarCelular(celular):
                limpia_frame()

                def agregarCelcarro():
                    añadirAlCarrito(celular, celular.supermercado.ofercelular)
                    otro = messagebox.askyesno(
                        message="¡Celular agregado con éxito!\n\n¿Desea finalizar su compra?",
                        title="Celular")

                    if otro:
                        finalizarCompra()
                        pass

                    else:
                        ofertaProductos()

                tk.Label(frame_zona2, text="Seleccionaste:", borderwidth=2, relief="solid",
                         font="Times 13",
                         bg="white").pack(pady=20)
                tk.Label(frame_zona2, text=celular, borderwidth=2, relief="solid",
                         font="Times 13",
                         bg="white").pack(pady=20)

                tk.Button(frame_zona2, text="Agregar al carrito", font="Times 13",
                          command=agregarCelcarro).pack(pady=10)
                tk.Button(frame_zona2, text="Volver", font="Times 13", command=ofertaProductos).pack()
            def comprarTv(tv):
                limpia_frame()

                def agregarTvcarro():
                    añadirAlCarrito(tv, tv.supermercado.ofertv)
                    otro = messagebox.askyesno(
                        message="¡Televisor agregado con éxito!\n\n¿Desea finalizar su compra?",
                        title="Televisor")

                    if otro:
                        finalizarCompra()
                        pass

                    else:
                        ofertaProductos()

                tk.Label(frame_zona2, text="Seleccionaste:", borderwidth=2, relief="solid",
                         font="Times 13",
                         bg="white").pack(pady=20)
                tk.Label(frame_zona2, text=tv, borderwidth=2, relief="solid",
                         font="Times 13",
                         bg="white").pack(pady=20)

                tk.Button(frame_zona2, text="Agregar al carrito", font="Times 13",
                          command=agregarTvcarro).pack(pady=10)
                tk.Button(frame_zona2, text="Volver", font="Times 13", command=ofertaProductos).pack()
            # APARTADO FILTROS
            def filtrosCelular(filtro_seleccionado):
                limpia_frame()
                if filtro_seleccionado == 1:  # Filtro por nombre
                    def filtroNombreCelular():
                        if entryInput.get() == '':
                            raise entrySinvalor
                        else:
                            filtrados = Celular.filtroNombre(self.mercado.ofercelular, entryInput.get())
                            if len(filtrados) == 0:
                                filtrados = Celular.filtroNombreSimilar(self.mercado.ofercelular, entryInput.get())
                                tk.Label(frame_zona2, text="Estos son los resultados de tu busqueda",
                                         borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                          ipadx=8,
                                                                                                          ipady=2,
                                                                                                          row=1,
                                                                                                          column=2)
                        entryInput.grid_forget()
                        label.grid_forget()
                        boton.grid_forget()

                        # funcion de los botones del listbox
                        def selectCel(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        descrip_selectsuper = """Elige cual es el celular que deseas comprar."""

                        descrip_selectsuperindi = "Selecciona el celular que deseas comprar: "

                        tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                 font="Times 13",
                                 bg="white").grid(pady=20)

                        tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                 font="Times 13",
                                 bg="white", fg="red").grid(pady=20)

                        listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                             bg="white")

                        if not filtrados == []:
                            for s in filtrados:
                                listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                        listbox.grid(rowspan=10, column=0)

                        listbox.bind('<<ListboxSelect>>', selectCel)
                        tk.Button(frame_zona2, text="Volver al menú de los productos", command=ofertaProductos).grid(
                            pady=7)

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por nombre de celulares----\nEscribe el nombre del celular que buscas",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4)
                    entryInput = tk.Entry(frame_zona2)
                    entryInput.grid(pady=5)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroNombreCelular)
                    boton.grid()
                elif filtro_seleccionado == 2:  # Filtro por marca
                    def filtroMarcaCelular():
                        if entryInput.get() == '':
                            raise entrySinvalor
                        else:
                            filtrados = Celular.filtroMarca(self.mercado.ofercelular, entryInput.get())
                            if len(filtrados) == 0:
                                filtrados = Celular.filtroMarcaSimilar(self.mercado.ofercelular, entryInput.get())
                                tk.Label(frame_zona2, text="Estos son los resultados de tu busqueda",
                                         borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                          ipadx=8,
                                                                                                          ipady=2,
                                                                                                          row=1,
                                                                                                          column=2)
                        entryInput.grid_forget()
                        label.grid_forget()
                        boton.grid_forget()

                        # funcion de los botones del listbox
                        def selectCel(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        descrip_selectsuper = """Elige cual es el celular que deseas comprar."""

                        descrip_selectsuperindi = "Selecciona el celular que deseas comprar: "

                        tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                 font="Times 13",
                                 bg="white").grid(pady=20)

                        tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                 font="Times 13",
                                 bg="white", fg="red").grid(pady=20)

                        listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                             bg="white")

                        if not filtrados == []:
                            for s in filtrados:
                                listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                        listbox.grid(rowspan=10, column=0)

                        listbox.bind('<<ListboxSelect>>', selectCel)
                        tk.Button(frame_zona2, text="Volver al menú de los productos", command=ofertaProductos).grid(
                            pady=7)

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por marca de celulares----\nEscribe la marca del celular que buscas",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4)
                    entryInput = tk.Entry(frame_zona2)
                    entryInput.grid(pady=5)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroMarcaCelular)
                    boton.grid()
                elif filtro_seleccionado == 3:  # Filtro por Almacenamiento
                    def filtroAlmacenamientoCelular():
                        def selectCel(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        if entryInput1.get() == '' or entryInput2.get() == '':
                            raise entrySinvalor
                        else:
                            try:
                                filtrados = Celular.filtroAlmacenamiento(self.mercado.ofercelular,
                                                                         int(entryInput1.get()), int(entryInput2.get()))
                                if len(filtrados) == 0:
                                    filtrados = self.mercado.ofercelular
                                    tk.Label(frame_zona2,
                                             text="No encontramos celulares en ese rango\nAcá te dejamos otras opciones que te pueden interesar :)",
                                             borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                              ipadx=8,
                                                                                                              ipady=2)
                                entryInput1.grid_forget()
                                entryInput2.grid_forget()
                                label.grid_forget()
                                boton.grid_forget()

                                # funcion de los botones del listbox

                                descrip_selectsuper = """Elige cual es el celular que deseas comprar."""

                                descrip_selectsuperindi = "Selecciona el celular que deseas comprar: "

                                tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white").grid(pady=20)

                                tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white", fg="red").grid(pady=20)

                                listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                                     bg="white")

                                if not filtrados == []:
                                    for s in filtrados:
                                        listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                                listbox.grid(rowspan=10, column=0)

                                listbox.bind('<<ListboxSelect>>', selectCel)
                                tk.Button(frame_zona2, text="Volver al menú de los productos",
                                          command=ofertaProductos).grid(pady=7)
                            except:
                                raise dtipoInvalido("interval minimo o maximo", "un número")

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por Almacenamiento----\nEscribe un rango para el almacenamiento del celular que buscas",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4, column=0, columnspan=3)
                    entryInput1 = tk.Entry(frame_zona2)
                    entryInput1.grid(pady=5, row=3, padx=5, column=0)
                    entryInput2 = tk.Entry(frame_zona2)
                    entryInput2.grid(pady=5, row=3, padx=5, column=2)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroAlmacenamientoCelular)
                    boton.grid(column=1)
                elif filtro_seleccionado == 4:  # Filtro por numero de camaras
                    def filtroCamarasCelular():
                        def selectCel(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        if entryInput1.get() == '' or entryInput2.get() == '':
                            raise entrySinvalor
                        else:
                            try:
                                filtrados = Celular.filtroCamaras(self.mercado.ofercelular,
                                                                  int(entryInput1.get()), int(entryInput2.get()))
                                if len(filtrados) == 0:
                                    filtrados = self.mercado.ofercelular
                                    tk.Label(frame_zona2,
                                             text="No encontramos celulares con este rango de numero de camaras\nAcá te dejamos otras opciones que te pueden interesar :)",
                                             borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                              ipadx=8,
                                                                                                              ipady=2)
                                entryInput1.grid_forget()
                                entryInput2.grid_forget()
                                label.grid_forget()
                                boton.grid_forget()

                                # funcion de los botones del listbox

                                descrip_selectsuper = """Elige cual es el celular que deseas comprar."""

                                descrip_selectsuperindi = "Selecciona el celular que deseas comprar: "

                                tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white").grid(pady=20)

                                tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white", fg="red").grid(pady=20)

                                listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                                     bg="white")

                                if not filtrados == []:
                                    for s in filtrados:
                                        listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                                listbox.grid(rowspan=10, column=0)

                                listbox.bind('<<ListboxSelect>>', selectCel)
                                tk.Button(frame_zona2, text="Volver al menú de los productos",
                                          command=ofertaProductos).grid(pady=7)
                            except:
                                raise dtipoInvalido("interval minimo o maximo", "un número")

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por numero de camaras----\nEscribe un rango para el numero de camaras del celular que buscas",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4, column=0, columnspan=3)
                    entryInput1 = tk.Entry(frame_zona2)
                    entryInput1.grid(pady=5, row=3, padx=5, column=0)
                    entryInput2 = tk.Entry(frame_zona2)
                    entryInput2.grid(pady=5, row=3, padx=5, column=2)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroCamarasCelular)
                    boton.grid(column=1)
                elif filtro_seleccionado == 5:  # Filtro por batería
                    def filtroBateriaCelular():
                        def selectCel(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        if entryInput1.get() == '' or entryInput2.get() == '':
                            raise entrySinvalor
                        else:
                            try:
                                filtrados = Celular.filtroBateria(self.mercado.ofercelular,
                                                                  int(entryInput1.get()), int(entryInput2.get()))
                                if len(filtrados) == 0:
                                    filtrados = self.mercado.ofercelular
                                    tk.Label(frame_zona2,
                                             text="No encontramos celulares en ese rango\nAcá te dejamos otras opciones que te pueden interesar :)",
                                             borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                              ipadx=8,
                                                                                                              ipady=2)
                                entryInput1.grid_forget()
                                entryInput2.grid_forget()
                                label.grid_forget()
                                boton.grid_forget()

                                # funcion de los botones del listbox

                                descrip_selectsuper = """Elige cual es el celular que deseas comprar."""

                                descrip_selectsuperindi = "Selecciona el celular que deseas comprar: "

                                tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white").grid(pady=20)

                                tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white", fg="red").grid(pady=20)

                                listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                                     bg="white")

                                if not filtrados == []:
                                    for s in filtrados:
                                        listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                                listbox.grid(rowspan=10, column=0)

                                listbox.bind('<<ListboxSelect>>', selectCel)
                                tk.Button(frame_zona2, text="Volver al menú de los productos",
                                          command=ofertaProductos).grid(pady=7)
                            except:
                                raise dtipoInvalido("interval minimo o maximo", "un número")

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por Bateria----\nEscribe un rango para la capacidad de la bateria del celular que buscas",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4, column=0, columnspan=3)
                    entryInput1 = tk.Entry(frame_zona2)
                    entryInput1.grid(pady=5, row=3, padx=5, column=0)
                    entryInput2 = tk.Entry(frame_zona2)
                    entryInput2.grid(pady=5, row=3, padx=5, column=2)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroBateriaCelular)
                    boton.grid(column=1)
                elif filtro_seleccionado == 6:  # Filtro por color de celular
                    def filtroColorCelular():
                        if entryInput.get() == '':
                            raise entrySinvalor
                        else:
                            filtrados = Celular.filtroColor(self.mercado.ofercelular, entryInput.get())
                            if len(filtrados) == 0:
                                filtrados = Celular.filtroColorSimilar(self.mercado.ofercelular, entryInput.get())
                                tk.Label(frame_zona2, text="Estos son los resultados de tu busqueda",
                                         borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                          ipadx=8,
                                                                                                          ipady=2,
                                                                                                          row=1,
                                                                                                          column=2)
                        entryInput.grid_forget()
                        label.grid_forget()
                        boton.grid_forget()

                        # funcion de los botones del listbox
                        def selectCel(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        descrip_selectsuper = """Elige cual es el celular que deseas comprar."""

                        descrip_selectsuperindi = "Selecciona el celular que deseas comprar: "

                        tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                 font="Times 13",
                                 bg="white").grid(pady=20)

                        tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                 font="Times 13",
                                 bg="white", fg="red").grid(pady=20)

                        listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                             bg="white")

                        if not filtrados == []:
                            for s in filtrados:
                                listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                        listbox.grid(rowspan=10, column=0)

                        listbox.bind('<<ListboxSelect>>', selectCel)
                        tk.Button(frame_zona2, text="Volver al menú de los productos", command=ofertaProductos).grid(
                            pady=7)

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por color de celulares----\nEscribe el color del celular que buscas",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4)
                    entryInput = tk.Entry(frame_zona2)
                    entryInput.grid(pady=5)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroColorCelular)
                    boton.grid()
                elif filtro_seleccionado == 7:  # Filtro ram
                    def filtroRamCelular():
                        def selectCel(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        if entryInput1.get() == '' or entryInput2.get() == '':
                            raise entrySinvalor
                        else:
                            try:
                                filtrados = Celular.filtroRam(self.mercado.ofercelular,
                                                              int(entryInput1.get()), int(entryInput2.get()))
                                if len(filtrados) == 0:
                                    filtrados = self.mercado.ofercelular
                                    tk.Label(frame_zona2,
                                             text="No encontramos celulares en ese rango\nAcá te dejamos otras opciones que te pueden interesar :)",
                                             borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                              ipadx=8,
                                                                                                              ipady=2)
                                entryInput1.grid_forget()
                                entryInput2.grid_forget()
                                label.grid_forget()
                                boton.grid_forget()

                                # funcion de los botones del listbox

                                descrip_selectsuper = """Elige cual es el celular que deseas comprar."""

                                descrip_selectsuperindi = "Selecciona el celular que deseas comprar: "

                                tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white").grid(pady=20)

                                tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white", fg="red").grid(pady=20)

                                listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                                     bg="white")

                                if not filtrados == []:
                                    for s in filtrados:
                                        listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                                listbox.grid(rowspan=10, column=0)

                                listbox.bind('<<ListboxSelect>>', selectCel)
                                tk.Button(frame_zona2, text="Volver al menú de los productos",
                                          command=ofertaProductos).grid(pady=7)
                            except:
                                raise dtipoInvalido("interval minimo o maximo", "un número")

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por RAM----\nEscribe un rango para la cantidad de RAM del celular que buscas",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4, column=0, columnspan=3)
                    entryInput1 = tk.Entry(frame_zona2)
                    entryInput1.grid(pady=5, row=3, padx=5, column=0)
                    entryInput2 = tk.Entry(frame_zona2)
                    entryInput2.grid(pady=5, row=3, padx=5, column=2)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroRamCelular)
                    boton.grid(column=1)
                elif filtro_seleccionado == 8:  # Filtro precio
                    def filtroPrecioCelular():
                        def selectCel(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        if entryInput1.get() == '' or entryInput2.get() == '':
                            raise entrySinvalor
                        else:
                            try:
                                filtrados = Celular.filtroPrecio(self.mercado.ofercelular,
                                                                 int(entryInput1.get()), int(entryInput2.get()))
                                if len(filtrados) == 0:
                                    filtrados = self.mercado.ofercelular
                                    tk.Label(frame_zona2,
                                             text="No encontramos celulares en ese rango\nAcá te dejamos otras opciones que te pueden interesar :)",
                                             borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                              ipadx=8,
                                                                                                              ipady=2)
                                entryInput1.grid_forget()
                                entryInput2.grid_forget()
                                label.grid_forget()
                                boton.grid_forget()

                                # funcion de los botones del listbox

                                descrip_selectsuper = """Elige cual es el celular que deseas comprar."""

                                descrip_selectsuperindi = "Selecciona el celular que deseas comprar: "

                                tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white").grid(pady=20)

                                tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white", fg="red").grid(pady=20)

                                listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                                     bg="white")

                                if not filtrados == []:
                                    for s in filtrados:
                                        listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                                listbox.grid(rowspan=10, column=0)

                                listbox.bind('<<ListboxSelect>>', selectCel)
                                tk.Button(frame_zona2, text="Volver al menú de los productos",
                                          command=ofertaProductos).grid(pady=7)
                            except:
                                raise dtipoInvalido("interval minimo o maximo", "un número")

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por precio----\nEscribe un rango para el precio del celular que buscas",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4, column=0, columnspan=3)
                    entryInput1 = tk.Entry(frame_zona2)
                    entryInput1.grid(pady=5, row=3, padx=5, column=0)
                    entryInput2 = tk.Entry(frame_zona2)
                    entryInput2.grid(pady=5, row=3, padx=5, column=2)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroPrecioCelular)
                    boton.grid(column=1)

            def filtrosTv(filtro_seleccionado):
                limpia_frame()
                if filtro_seleccionado == 1:  # Filtro nombre
                    def filtroNombreTv():
                        if entryInput.get() == '':
                            raise entrySinvalor
                        else:
                            filtrados = Tv.filtroNombre(self.mercado.ofertv, entryInput.get())
                            if len(filtrados) == 0:
                                filtrados = Tv.filtroNombreSimilar(self.mercado.ofertv, entryInput.get())
                                tk.Label(frame_zona2, text="Estos son los resultados de tu busqueda",
                                         borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                          ipadx=8,
                                                                                                          ipady=2,
                                                                                                          row=1,
                                                                                                          column=2)
                        entryInput.grid_forget()
                        label.grid_forget()
                        boton.grid_forget()

                        # funcion de los botones del listbox
                        def selectTv(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        descrip_selectsuper = """Elige cual es el tv que deseas comprar."""

                        descrip_selectsuperindi = "Selecciona el tv que deseas comprar: "

                        tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                 font="Times 13",
                                 bg="white").grid(pady=20)

                        tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                 font="Times 13",
                                 bg="white", fg="red").grid(pady=20)

                        listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                             bg="white")

                        if not filtrados == []:
                            for s in filtrados:
                                listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                        listbox.grid(rowspan=10, column=0)

                        listbox.bind('<<ListboxSelect>>', selectTv)
                        tk.Button(frame_zona2, text="Volver al menú de los productos", command=ofertaProductos).grid(
                            pady=7)

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por nombre de televisores----\nEscribe el nombre del televisor que buscas",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4)
                    entryInput = tk.Entry(frame_zona2)
                    entryInput.grid(pady=5)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroNombreTv)
                    boton.grid()
                elif filtro_seleccionado == 2:  # Filtro por marca
                    def filtroMarcaTv():
                        if entryInput.get() == '':
                            raise entrySinvalor
                        else:
                            filtrados = Tv.filtroMarca(self.mercado.ofertv, entryInput.get())
                            if len(filtrados) == 0:
                                filtrados = Tv.filtroMarcaSimilar(self.mercado.ofertv, entryInput.get())
                                tk.Label(frame_zona2, text="Estos son los resultados de tu busqueda",
                                         borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                          ipadx=8,
                                                                                                          ipady=2,
                                                                                                          row=1,
                                                                                                          column=2)
                        entryInput.grid_forget()
                        label.grid_forget()
                        boton.grid_forget()

                        # funcion de los botones del listbox
                        def selectTv(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        descrip_selectsuper = """Elige cual es el tv que deseas comprar."""

                        descrip_selectsuperindi = "Selecciona el tv que deseas comprar: "

                        tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                 font="Times 13",
                                 bg="white").grid(pady=20)

                        tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                 font="Times 13",
                                 bg="white", fg="red").grid(pady=20)

                        listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                             bg="white")

                        if not filtrados == []:
                            for s in filtrados:
                                listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                        listbox.grid(rowspan=10, column=0)

                        listbox.bind('<<ListboxSelect>>', selectTv)
                        tk.Button(frame_zona2, text="Volver al menú de los productos", command=ofertaProductos).grid(
                            pady=7)

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por marca de televisores----\nEscribe la marca del televisor que buscas",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4)
                    entryInput = tk.Entry(frame_zona2)
                    entryInput.grid(pady=5)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroMarcaTv)
                    boton.grid()
                elif filtro_seleccionado == 3:  # Filtro pulgadas
                    def filtroPulgadasTv():
                        def selectTv(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        if entryInput1.get() == '' or entryInput2.get() == '':
                            raise entrySinvalor
                        else:
                            try:
                                filtrados = Tv.filtroPulgadas(self.mercado.ofertv,
                                                              int(entryInput1.get()), int(entryInput2.get()))
                                if len(filtrados) == 0:
                                    filtrados = self.mercado.ofertv
                                    tk.Label(frame_zona2,
                                             text="No encontramos tvs en ese rango\nAcá te dejamos otras opciones que te pueden interesar :)",
                                             borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                              ipadx=8,
                                                                                                              ipady=2)
                                entryInput1.grid_forget()
                                entryInput2.grid_forget()
                                label.grid_forget()
                                boton.grid_forget()

                                # funcion de los botones del listbox

                                descrip_selectsuper = """Elige cual es el tv que deseas comprar."""

                                descrip_selectsuperindi = "Selecciona el tv que deseas comprar: "

                                tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white").grid(pady=20)

                                tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white", fg="red").grid(pady=20)

                                listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                                     bg="white")

                                if not filtrados == []:
                                    for s in filtrados:
                                        listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                                listbox.grid(rowspan=10, column=0)

                                listbox.bind('<<ListboxSelect>>', selectTv)
                                tk.Button(frame_zona2, text="Volver al menú de los productos",
                                          command=ofertaProductos).grid(pady=7)
                            except:
                                raise dtipoInvalido("interval minimo o maximo", "un número")

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por Pulgadas----\nEscribe un rango para las pulgadas del tv que buscas",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4, column=0, columnspan=3)
                    entryInput1 = tk.Entry(frame_zona2)
                    entryInput1.grid(pady=5, row=3, padx=5, column=0)
                    entryInput2 = tk.Entry(frame_zona2)
                    entryInput2.grid(pady=5, row=3, padx=5, column=2)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroPulgadasTv)
                    boton.grid(column=1)
                elif filtro_seleccionado == 4:  # Filtro resolucion
                    def filtroResolucionTv():
                        if entryInput.get() == '':
                            raise entrySinvalor
                        else:
                            filtrados = Tv.filtroResolucion(self.mercado.ofertv, entryInput.get())
                            if len(filtrados) == 0:
                                filtrados = Tv.filtroResolucionSimilar(self.mercado.ofertv, entryInput.get())
                                tk.Label(frame_zona2, text="Estos son los resultados de tu busqueda",
                                         borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                          ipadx=8,
                                                                                                          ipady=2,
                                                                                                          row=1,
                                                                                                          column=2)
                        entryInput.grid_forget()
                        label.grid_forget()
                        boton.grid_forget()

                        # funcion de los botones del listbox
                        def selectTv(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        descrip_selectsuper = """Elige cual es el tv que deseas comprar."""

                        descrip_selectsuperindi = "Selecciona el tv que deseas comprar: "

                        tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                 font="Times 13",
                                 bg="white").grid(pady=20)

                        tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                 font="Times 13",
                                 bg="white", fg="red").grid(pady=20)

                        listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                             bg="white")

                        if not filtrados == []:
                            for s in filtrados:
                                listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                        listbox.grid(rowspan=10, column=0)

                        listbox.bind('<<ListboxSelect>>', selectTv)
                        tk.Button(frame_zona2, text="Volver al menú de los productos", command=ofertaProductos).grid(
                            pady=7)

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por resolucion de televisores----\nEscribe la resolucion del televisor que buscas (ej: 4k)",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4)
                    entryInput = tk.Entry(frame_zona2)
                    entryInput.grid(pady=5)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroResolucionTv)
                    boton.grid()
                elif filtro_seleccionado == 5:  # Filtro precio
                    def filtroPrecioTv():
                        def selectTv(event):

                            selected_item = filtrados[int(list(listbox.curselection())[0])]
                            comprarCelular(selected_item)

                        if entryInput1.get() == '' or entryInput2.get() == '':
                            raise entrySinvalor
                        else:
                            try:
                                filtrados = Tv.filtroPrecio(self.mercado.ofertv,
                                                              int(entryInput1.get()), int(entryInput2.get()))
                                if len(filtrados) == 0:
                                    filtrados = self.mercado.ofertv
                                    tk.Label(frame_zona2,
                                             text="No encontramos tvs en ese rango\nAcá te dejamos otras opciones que te pueden interesar :)",
                                             borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8,
                                                                                                              ipadx=8,
                                                                                                              ipady=2)
                                entryInput1.grid_forget()
                                entryInput2.grid_forget()
                                label.grid_forget()
                                boton.grid_forget()

                                # funcion de los botones del listbox

                                descrip_selectsuper = """Elige cual es el tv que deseas comprar."""

                                descrip_selectsuperindi = "Selecciona el tv que deseas comprar: "

                                tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white").grid(pady=20)

                                tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                         font="Times 13",
                                         bg="white", fg="red").grid(pady=20)

                                listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                                     bg="white")

                                if not filtrados == []:
                                    for s in filtrados:
                                        listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                                listbox.grid(rowspan=10, column=0)

                                listbox.bind('<<ListboxSelect>>', selectTv)
                                tk.Button(frame_zona2, text="Volver al menú de los productos",
                                          command=ofertaProductos).grid(pady=7)
                            except:
                                raise dtipoInvalido("interval minimo o maximo", "un número")

                    label = tk.Label(frame_zona2,
                                     text="----Bienvenido al filtro por Precio----\nEscribe un rango para el precio del tv que buscas",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white")
                    label.grid(pady=10, ipadx=8, ipady=4, column=0, columnspan=3)
                    entryInput1 = tk.Entry(frame_zona2)
                    entryInput1.grid(pady=5, row=3, padx=5, column=0)
                    entryInput2 = tk.Entry(frame_zona2)
                    entryInput2.grid(pady=5, row=3, padx=5, column=2)
                    boton = tk.Button(frame_zona2, text="Aceptar", command=filtroPrecioTv)
                    boton.grid(column=1)

            def agregarTvs():
                limpia_frame()

                tk.Label(frame_zona2, text=f"Agregar Televisores a {self.mercado.nombre}", borderwidth=2,
                         relief="solid", font="Times 13", bg="white").pack(pady=20)
                tk.Label(frame_zona2, text="Aquí podrá agregar televisores al supermercado seleccionado",
                         borderwidth=2, relief="solid", font="Times 13", bg="white").pack(pady=20)
                critv = ["Nombre", "Marca", "Pulgadas (numero)", "Resolucion (String)", "Precio (int)",
                         "Cantidad (int)"]
                tvfield = FieldFrame(frame_zona2, "Datos del televisor", critv, "Valores", None, None)
                tvfield.pack()

                # Boton Aceptar
                def agregarTv():
                    tvfield.valores = [x.get() for x in tvfield.lst_entrys]
                    if "" in tvfield.valores:
                        raise entrySinvalor
                    else:
                        try:
                            if re.match(r"\D", tvfield.valores[0]) == None:
                                buentipo = [critv[0], "palabras/letras"]  # Parametro nombre
                                0 / 0
                            if re.match(r"\D", tvfield.valores[1]) == None:
                                buentipo = [critv[1], "palabras/letras"]  # Parametro marca
                                0 / 0
                            buentipo = [critv[2], "un numero"]  # Parametro pulgadas
                            int(tvfield.valores[2])
                            if re.match(r"(.*\D*)+", tvfield.valores[3]) == None:
                                buentipo = [critv[3], "palabras/letras"]  # Resolucion
                                0 / 0

                            buentipo = [critv[4], "un numero"]  # Precio
                            int(tvfield.valores[4])
                            buentipo = [critv[5], "un numero"]  # Cantidad
                            int(tvfield.valores[5])

                            tv = Tv(tvfield.valores[0], tvfield.valores[1], tvfield.valores[2]
                                    , tvfield.valores[3], tvfield.valores[4], tvfield.valores[5]
                                    , self.mercado)
                            self.mercado.ofertv.append(tv)

                            otro = messagebox.askyesno(
                                message="¡Producto agregado con éxito!\n\n¿Desea agregar un producto diferente?",
                                title="Producto")

                            if otro:
                                agregarprods()
                            else:
                                crearsuper()
                        except:
                            raise dtipoInvalido(buentipo[0], buentipo[1])

                aceptar = tk.Button(tvfield, text="Aceptar", font="Times 13", command=agregarTv)
                aceptar.grid(row=len(tvfield.criterios) + 1, column=0, pady=10)

            def agregarCelular():
                limpia_frame()

                tk.Label(frame_zona2, text=f"Agregar Celulares a {self.mercado.nombre}", borderwidth=2,
                         relief="solid", font="Times 13", bg="white").pack(pady=20)
                tk.Label(frame_zona2, text="Aquí podrá agregar celulares al supermercado seleccionado",
                         borderwidth=2, relief="solid", font="Times 13", bg="white").pack(pady=20)
                cricelulares = ["Nombre", "Marca", "almacenamiento (numero)", "Camaras (int)",
                                "Bateria (int)", "Color (String)", "Ram (int)", "Precio (int)",
                                "Cantidad (int)"]
                celfield = FieldFrame(frame_zona2, "Datos del celular", cricelulares, "Valores", None, None)
                celfield.pack()

                # Boton Aceptar
                def agregarCel():
                    celfield.valores = [x.get() for x in celfield.lst_entrys]
                    if "" in celfield.valores:
                        raise entrySinvalor
                    else:
                        try:
                            if re.match(r"\D", celfield.valores[0]) == None:
                                buentipo = [cricelulares[0], "palabras/letras"]  # Parametro nombre
                                0 / 0
                            if re.match(r"\D", celfield.valores[1]) == None:
                                buentipo = [cricelulares[1], "palabras/letras"]  # Parametro marca
                                0 / 0
                            buentipo = [cricelulares[2], "un numero"]  # Parametro Almacenamiento
                            int(celfield.valores[2])
                            buentipo = [cricelulares[3], "un numero"]  # Parametro Camaras
                            int(celfield.valores[3])
                            buentipo = [cricelulares[4], "un numero"]  # Parametro Bateria
                            int(celfield.valores[4])
                            if re.match(r"\D", celfield.valores[5]) == None:
                                buentipo = [cricelulares[5], "palabras/letras"]  # Parametro Color
                                0 / 0
                            buentipo = [cricelulares[6], "un numero"]  # Parametro Ram
                            int(celfield.valores[6])
                            buentipo = [cricelulares[7], "un numero"]  # Precio
                            int(celfield.valores[7])
                            buentipo = [cricelulares[8], "un numero"]  # Cantidad
                            int(celfield.valores[8])

                            celular = Celular(celfield.valores[0], celfield.valores[1], celfield.valores[2]
                                              , celfield.valores[3], celfield.valores[4], celfield.valores[5],
                                              celfield.valores[6], celfield.valores[7], celfield.valores[8],
                                              self.mercado)

                            self.mercado.ofercelular.append(celular)

                            otro = messagebox.askyesno(
                                message="¡Producto agregado con éxito!\n\n¿Desea agregar un producto diferente?",
                                title="Producto")

                            if otro:
                                agregarprods()
                            else:
                                crearsuper()
                        except:
                            raise dtipoInvalido(buentipo[0], buentipo[1])

                aceptar = tk.Button(celfield, text="Aceptar", font="Times 13", command=agregarCel)
                aceptar.grid(row=len(celfield.criterios) + 1, column=0, pady=10)

            def agregarElectronica():
                limpia_frame()
                tk.Label(frame_zona2,
                         text=f"¿Que tipo de productos electronicos deseas agregar a {self.mercado.nombre}?",
                         borderwidth=2, relief="solid", font="Times 13", bg="white").grid(row=0, columnspan=2,
                                                                                          pady=30)

                # Flujo para agregar un producto electrónico

                # Cargar la imagen
                imagenTelevisor = Image.open("televisor.jpg")
                # Redimensionar la imagen si es necesario
                imagenTelevisor = imagenTelevisor.resize((170, 170))
                # Crear objeto PhotoImage y mantener una referencia
                imagenTelevisor = ImageTk.PhotoImage(imagenTelevisor)

                # Crear el widget Label para mostrar la imagen del celular
                televisor_label = tk.Label(frame_zona2, image=imagenTelevisor, relief="solid")
                televisor_label.image = imagenTelevisor
                televisor_label.grid(row=1, column=0)

                tk.Button(frame_zona2, text="Televisor", font="Times 13", command=agregarTvs).grid(row=2,
                                                                                                   column=0,
                                                                                                   pady=20)

                # Cargar la imagen
                imagenCelular = Image.open("celular.jpg")
                # Redimensionar la imagen si es necesario
                imagenCelular = imagenCelular.resize((170, 170))
                # Crear objeto PhotoImage y mantener una referencia
                imagenCelular = ImageTk.PhotoImage(imagenCelular)

                # Crear el widget Label para mostrar la imagen del celular
                celular_label = tk.Label(frame_zona2, image=imagenCelular, relief="solid")
                celular_label.image = imagenCelular
                celular_label.grid(row=1, column=1)

                tk.Button(frame_zona2, text="Celular", font="Times 13", command=agregarCelular).grid(row=2,
                                                                                                     column=1,
                                                                                                     pady=20)

            def ofertaProductos():
                limpia_frame()

                tk.Label(frame_zona2,
                         text=f"Estos son los productos que tenemos disponibles en el Supermercado {self.mercado.nombre}",
                         borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=25, ipadx=15, ipady=10)

                # Cargar la imagen
                imagenCelular = Image.open("Secciones.jpg")
                # Redimensionar la imagen si es necesario
                imagenCelular = imagenCelular.resize((250, 250))
                # Crear objeto PhotoImage y mantener una referencia
                imagenCelular = ImageTk.PhotoImage(imagenCelular)

                # Crear el widget Label para mostrar la imagen del celular
                celular_label = tk.Label(frame_zona2, image=imagenCelular, relief="solid")
                celular_label.image = imagenCelular
                celular_label.grid(row=1, column=0)

                def ofertaElectronico():
                    if self.cliente.nombre == None:
                        raise comprarSinUsuario(IdenUsuario)
                    limpia_frame()

                    tk.Label(frame_zona2, text="¿Que tipo de producto electronico deseas?", borderwidth=2,
                             relief="solid", font="Times 13", bg="white").grid(pady=30, ipadx=15, ipady=10,
                                                                               columnspan=2)

                    def ofertaCelulares():
                        limpia_frame()
                        if len(self.mercado.ofercelular) == 0:
                            tk.Label(frame_zona2, text="Este supermercado no tiene celulares :(\n¿Deseas crear uno?",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=30, ipadx=15,
                                                                                                      ipady=10,
                                                                                                      columnspan=2)
                            tk.Button(frame_zona2, text="Si", font=("Times 13", 16), command=agregarCelular).grid(row=1,
                                                                                                                  column=0)  # Creo que si lo pude implementar
                            tk.Button(frame_zona2, text="No", font=("Times 13", 16), command=ofertaProductos).grid(
                                row=1, column=1)
                        else:
                            # funcion de los botones del listbox
                            def selectCel(event):

                                selected_item = self.mercado.ofercelular[int(list(listbox.curselection())[0])]
                                comprarCelular(selected_item)


                            tk.Label(frame_zona2, text="Seleccionar Celular", borderwidth=2, relief="solid",
                                     font="Times 13",
                                     bg="white").grid(pady=20)

                            descrip_selectsuper = """Elige cual es el celular que deseas comprar."""

                            descrip_selectsuperindi = "Selecciona el celular que deseas comprar: "

                            tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                     font="Times 13",
                                     bg="white").grid(pady=20)

                            tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                     font="Times 13",
                                     bg="white", fg="red").grid(pady=20)

                            listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                                 bg="white")

                            if not self.mercado.ofercelular == []:
                                for s in self.mercado.ofercelular:
                                    listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                            listbox.grid(rowspan=10, column=0)

                            listbox.bind('<<ListboxSelect>>', selectCel)
                            # Se muestra las opciones adicionales de filtros e ir a otras secciones
                            tk.Label(frame_zona2, text="***Opciones adcionales***",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8,
                                                                                                      ipady=2, row=0,
                                                                                                      column=2)

                            tk.Button(frame_zona2, text="Filtrar por nombre de celular", font=("Times 12", 10),
                                      command=lambda: filtrosCelular(1)).grid(column=2, row=1, pady=8)
                            tk.Button(frame_zona2, text="Filtrar por marca de celular", font=("Times 12", 10),
                                      command=lambda: filtrosCelular(2)).grid(column=2, row=2, pady=8)
                            tk.Button(frame_zona2, text="Filtrar por almacenamiento", font=("Times 12", 10),
                                      command=lambda: filtrosCelular(3)).grid(column=2, row=3, pady=8)
                            tk.Button(frame_zona2, text="Filtrar por número de camaras", font=("Times 12", 10),
                                      command=lambda: filtrosCelular(4)).grid(column=2, row=4, pady=8)
                            tk.Button(frame_zona2, text="Filtrar por bateria", font=("Times 12", 10),
                                      command=lambda: filtrosCelular(5)).grid(column=2, row=5, pady=8)
                            tk.Button(frame_zona2, text="Filtrar por color de celular", font=("Times 12", 10),
                                      command=lambda: filtrosCelular(6)).grid(column=2, row=6, pady=8)
                            tk.Button(frame_zona2, text="Filtrar por ram", font=("Times 12", 10),
                                      command=lambda: filtrosCelular(7)).grid(column=2, pady=8, row=7)
                            tk.Button(frame_zona2, text="Filtrar por precio", font=("Times 12", 10),
                                      command=lambda: filtrosCelular(8)).grid(column=2, pady=8, row=8)

                            tk.Button(frame_zona2, text="Volver al menu Electronico", font=("Times 12", 10),
                                      command=ofertaElectronico).grid(column=2, pady=8, row=9)
                            tk.Button(frame_zona2, text="Escoger otra Seccion", font=("Times 12", 10),
                                      command=ofertaProductos).grid(column=2, pady=8, row=10)

                    def ofertaTvs():
                        limpia_frame()
                        if len(self.mercado.ofertv) == 0:
                            tk.Label(frame_zona2, text="Este supermercado no tiene televisores :(\n¿Deseas crear uno?",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=30, ipadx=15,
                                                                                                      ipady=10,
                                                                                                      columnspan=2)
                            tk.Button(frame_zona2, text="Si", font=("Times 13", 16), command=agregarTvs).grid(row=1,
                                                                                                              column=0)
                            tk.Button(frame_zona2, text="No", font=("Times 13", 16), command=ofertaProductos).grid(row=1, column=1)
                        else:
                            # funcion de los botones del listbox
                            def selectTv(event):

                                selected_item = self.mercado.ofertv[int(list(listbox.curselection())[0])]
                                comprarCelular(selected_item)

                            tk.Label(frame_zona2, text="Seleccionar Televisor", borderwidth=2, relief="solid",
                                     font="Times 13",
                                     bg="white").grid(pady=20)

                            descrip_selectsuper = """Elige cual es el televisor que deseas comprar."""

                            descrip_selectsuperindi = "Selecciona el televisor que deseas comprar: "

                            tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                     font="Times 13",
                                     bg="white").grid(pady=20)

                            tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                     font="Times 13",
                                     bg="white", fg="red").grid(pady=20)

                            listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                                 bg="white")

                            if not self.mercado.ofertv == []:
                                for s in self.mercado.ofertv:
                                    listbox.insert(tk.END, s.nombre+" Marca: "+s.marca)

                            listbox.grid(rowspan=10, column=0)

                            listbox.bind('<<ListboxSelect>>', selectTv)
                            # Se muestra las opciones adicionales de filtros e ir a otras secciones
                            tk.Label(frame_zona2, text="***Opciones adcionales***",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8,
                                                                                                      ipady=2, row=0,
                                                                                                      column=2)

                            tk.Button(frame_zona2, text="Filtrar por nombre de televisor", font=("Times 12", 10),
                                      command=lambda: filtrosTv(1)).grid(column=2, row=1, pady=8)
                            tk.Button(frame_zona2, text="Filtrar por marca de televisor", font=("Times 12", 10),
                                      command=lambda: filtrosTv(2)).grid(column=2, row=2, pady=8)
                            tk.Button(frame_zona2, text="Filtrar por pulgadas", font=("Times 12", 10),
                                      command=lambda: filtrosTv(3)).grid(column=2, row=3, pady=8)
                            tk.Button(frame_zona2, text="Filtrar por resolucion", font=("Times 12", 10),
                                      command=lambda: filtrosTv(4)).grid(column=2, row=4, pady=8)
                            tk.Button(frame_zona2, text="Filtrar por precio", font=("Times 12", 10),
                                      command=lambda: filtrosTv(5)).grid(column=2, pady=8, row=8)

                            tk.Button(frame_zona2, text="Volver al menu Electronico", font=("Times 12", 10),
                                      command=ofertaElectronico).grid(column=2, pady=8, row=9)
                            tk.Button(frame_zona2, text="Escoger otra Seccion", font=("Times 12", 10),
                                      command=ofertaProductos).grid(column=2, pady=8, row=10)

                    tk.Button(frame_zona2, text="Celular", font=("Times 13", 16), command=ofertaCelulares).grid(
                        column=0, row=1)
                    tk.Button(frame_zona2, text="Televisor", font=("Times 13", 16), command=ofertaTvs).grid(column=1,
                                                                                                            row=1)

                tk.Button(frame_zona2, text="Libros", font="Times 13", command=comprarLibro).grid(pady=15)
                tk.Button(frame_zona2, text="Electronios", font="Times 13", command=ofertaElectronico).grid(pady=15)
                tk.Button(frame_zona2, text="Alimentos", font="Times 13", command=MenuAlimentos).grid(pady=15)

            # ENDS PARTE ELECTONICOS ---------------------------------------------------------
            # Boton seleccionar supermercado
            def selectsuper():

                limpia_frame()

                # funcion de los botones del listbox
                def listboxselecsuper(event):
                    selected_item = listbox.get(listbox.curselection())

                    if selected_item == "Agregar Supermercado":
                        crearsuper()
                    else:
                        for s in self.lista_super:
                            if s.nombre == selected_item:
                                self.mercado = s
                                messagebox.showinfo("Selección", f"Has seleccionado {selected_item}")
                                ofertaProductos()  # Este metodo nos muestra que products hay en el supermercado seleccionado
                                break

                tk.Label(frame_zona2, text="Seleccionar Supermercado", borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)

                descrip_selectsuper = """Este proceso permite elegir el supermercado en el cual se desea comprar.\n
También, permite agregar un nuevo supermercado al listado"""

                descrip_selectsuperindi = "Seleccione el supermercado en el cual desea comprar: "

                tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)

                tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid", font="Times 13",
                         bg="white", fg="red").pack(pady=20)

                listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13", bg="white")

                if not self.lista_super == []:
                    for s in self.lista_super:
                        listbox.insert(tk.END, s.nombre)

                listbox.insert(tk.END, "Agregar Supermercado")
                listbox.pack()

                listbox.bind('<<ListboxSelect>>', listboxselecsuper)
            #ComprarRopa-By Yiduar

            """def comprarRopa():
                if self.mercado.nombre == None:
                    raise comprarSinEligirSup(selectsuper)
                if self.cliente.nombre == None:
                    raise comprarSinUsuario(IdenUsuario)
                limpia_frame()
                self.filtroRopa = None
                
                def mostrarOfertasRopa(ofertas):
                    limpia_frame()
                    ofertas_ropa = ofertas.copy()

                    def borrarFiltros():
                        self.filtroRopa = None
                        mostrarOfertasRopa(self.mercado.oferropa)

                    def agregarRopaCarrito():
                        selected_items = []
                        for item in lista_prendas.selection():
                            selected_items.append(lista_prendas.item(item)["values"])
      
                        for item in selected_items:
                            nombre = item[0]
                            precio = float(item[1])
                            categoria = item[2]
                            self.cliente.carrito.append(Ropa(nombre, precio, categoria))
                            
                        cantidad_seleccionada = len(self.cliente.carrito)  # Obtener la cantidad de productos seleccionados
                        
                        if cantidad_seleccionada >= 3:
                            descuento = 0.1  # Descuento del 10% para 3 o más productos
                        else:
                            descuento = 0

                        precio_total = sum([producto.precio for producto in self.cliente.carrito])
                        descuento_total = precio_total * descuento
                        precio_con_descuento = precio_total - descuento_total
                        
                        mensaje_descuento = f"Descuento aplicado: {descuento * 100}%"
                        
                        mensaje_final = f"¡Ropa agregada al carrito con éxito!\n\n" \
                                        f"Precio total: ${precio_total:.2f}\n" \
                                        f"{mensaje_descuento}\n" \
                                        f"Precio con descuento: ${precio_con_descuento:.2f}\n\n" \
                                        f"¿Desea finalizar su compra?"
                        if messagebox.askyesno(message=mensaje_final, title="Ropa"):
                            finalizarCompra()
                        else:
                            comprarRopa()
                        
                    def aplicarFiltro():
                        categoria_seleccionada = combo_categorias.get()

                        if categoria_seleccionada == "Todas":
                           self.filtroRopa = None
                        else:
                           self.filtroRopa = categoria_seleccionada

                        mostrarOfertasRopa(self.mercado.oferropa)

                    def cargarImagen(ruta_imagen):
                         img = Image.open(ruta_imagen)
                         img = img.resize((100, 100), Image.ANTIALIAS)
                         return ImageTk.PhotoImage(img)
                    
                    # Crear ventana principal
                    ventana = tk.Toplevel()
                    ventana.title("Ofertas de Ropa")
                    ventana.geometry("1200x600")
                    
                    frame_principal = ttk.Frame(ventana)
                    frame_principal.pack(padx=20, pady=20)
                    
                    categorias = ["Todas", "Hombres", "Mujeres", "Niños"]
                    combo_categorias = ttk.Combobox(frame_principal, values=categorias)
                    combo_categorias.current(0)  # Establecer la opción predeterminada
                    combo_categorias.pack(pady=10)
                    boton_aplicar_filtro = ttk.Button(frame_principal, text="Aplicar filtro", command=aplicarFiltro)
                    boton_aplicar_filtro.pack(pady=10)
                    lista_prendas = ttk.Treeview(frame_principal, columns=("Nombre", "Precio", "Categoría"))
                    lista_prendas.heading("#0", text="ID")
                    lista_prendas.heading("Nombre", text="Nombre")
                    lista_prendas.heading("Precio", text="Precio")
                    lista_prendas.heading("Categoría", text="Categoría")
                    lista_prendas.column("#0", width=50)
                    lista_prendas.column("Nombre", width=200)
                    lista_prendas.column("Precio", width=100)
                    lista_prendas.column("Categoría", width=100)
                    lista_prendas.pack(padx=10, pady=10)

                    for i, oferta in enumerate(ofertas_ropa, start=1):
                        if self.filtroRopa is None or oferta.categoria == self.filtroRopa:
                            lista_prendas.insert("", "end", text=str(i), values=(oferta.nombre, str(oferta.precio), oferta.categoria))
                            img = cargarImagen(oferta.rutaImagen)
                            lista_prendas.image = img
                            lista_prendas.insert(str(i), "end", image=img)
           
                    boton_borrar_filtros = ttk.Button(frame_principal, text="Borrar filtros", command=borrarFiltros)
                    boton_borrar_filtros.pack(pady=10)
                    boton_agregar_carrito = ttk.Button(frame_principal, text="Agregar al Carrito", command=agregarRopaCarrito)
                    boton_agregar_carrito.pack(pady=10)
                    ventana.mainloop()
                    mostrarOfertasRopa(self.mercado.oferropa)   """

            # Funcionalidad comprarLibro
            def comprarLibro():

                if self.mercado.nombre == None:
                    raise comprarSinEligirSup(selectsuper)
                if self.cliente.nombre == None:
                    raise comprarSinUsuario(IdenUsuario)

                limpia_frame()
                self.filtrolibro = 0

                def mostraroferlibros(oferlibro):
                    limpia_frame()
                    oferlibros = oferlibro.copy()

                    def borrarFiltros():
                        self.filtrolibro = 0
                        mostraroferlibros(self.mercado.oferlibros)

                    # Filtro #1
                    def filtroAutor():
                        autores = Libro.listaAutores(oferlibros)

                        limpia_frame()

                        def selectAutor(event):
                            selected_item = oferlibros[int(listboxAutores.get(listboxAutores.curselection())[0]) - 1]
                            self.filtrolibro = 1
                            mostraroferlibros(Libro.filtrarporAutor(oferlibros, selected_item.autor))

                        tk.Label(frame_zona2, text="Filtrar por autor", borderwidth=2, relief="solid", font="Times 13",
                                 bg="white").pack(pady=20)
                        tk.Label(frame_zona2, text="Aqui podrás filtrar los libros por el autor de tu selección",
                                 borderwidth=2, relief="solid", font="Times 13", bg="white").pack(pady=20)
                        listboxAutores = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                                    bg="white")

                        for l in range(len(autores)):
                            listboxAutores.insert(tk.END, str(l + 1) + ". " + autores[l])

                        listboxAutores.pack(pady=20)

                        listboxAutores.bind('<<ListboxSelect>>', selectAutor)

                    # Filtro 2
                    def filtroPrecio():
                        limpia_frame()

                        def defrango():
                            precioField.valores = [x.get() for x in precioField.lst_entrys]
                            self.filtrolibro = 2
                            mostraroferlibros(Libro.filtrarporPrecio(oferlibros, precioField.valores[0]
                                                                     , precioField.valores[1]))

                        tk.Label(frame_zona2, text="Filtrar por precio", borderwidth=2, relief="solid", font="Times 13",
                                 bg="white").pack(pady=20)

                        tk.Label(frame_zona2, text="Aquí podrás filtrar los libros en un rango de precio",
                                 borderwidth=2, relief="solid", font="Times 13",
                                 bg="white").pack(pady=20)

                        precioField = FieldFrame(frame_zona2, "Precios", ["Precio Mínimo", "Precio Máximo"], "Valores",
                                                 None, None)

                        Aceptar = tk.Button(precioField, text="Aceptar", font="Times 13", command=defrango)
                        Aceptar.grid(row=len(precioField.criterios) + 1, column=0, pady=10)

                    def selectlibro(event):

                        selected_item = oferlibros[int(listbox_libros.get(listbox_libros.curselection())[0]) - 1]

                        limpia_frame()

                        def agregarlibalcarro():
                            añadirAlCarrito(selected_item, selected_item.supermercado.oferlibros)
                            otro = messagebox.askyesno(
                                message="¡Libro agregado con éxito!\n\n¿Desea finalizar su compra?", title="Libro")

                            if otro:
                                finalizarCompra()
                                pass

                            else:
                                comprarLibro()

                        tk.Label(frame_zona2, text="Seleccionaste:", borderwidth=2, relief="solid", font="Times 13",
                                 bg="white").pack(pady=20)
                        tk.Label(frame_zona2, text=selected_item, borderwidth=2, relief="solid", font="Times 13",
                                 bg="white").pack(pady=20)

                        tk.Button(frame_zona2, text="Agregar al carrito", font="Times 13",
                                  command=agregarlibalcarro).pack(pady=10)
                        tk.Button(frame_zona2, text="Volver", font="Times 13", command=comprarLibro).pack()

                    L_antesdelistbox = tk.Label(frame_zona2, text="Estos son los libros que tenemos disponibles",
                                                borderwidth=2, relief="solid", font="Times 13", bg="white")
                    L_antesdelistbox.pack(pady=20)

                    listbox_libros = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13", bg="white")

                    for l in range(len(oferlibros)):
                        listbox_libros.insert(tk.END, str(l + 1) + ". " + oferlibros[l].nombre)

                    listbox_libros.pack()

                    tk.Label(frame_zona2, text="Filtros", borderwidth=2, relief="solid", font="Times 13",
                             bg="white").pack(pady=10)

                    tk.Button(frame_zona2, text="Autor", font="Times 13", command=filtroAutor).pack(pady=10)
                    tk.Button(frame_zona2, text="Precio", font="Times 13").pack(pady=10)

                    if self.filtrolibro != 0:
                        tk.Button(frame_zona2, text="Quitar filtros", font="Times 13"
                                  , command=borrarFiltros).pack(pady=10)

                    listbox_libros.bind('<<ListboxSelect>>', selectlibro)

                tk.Label(frame_zona2, text=f"Comprar Libros en {self.mercado.nombre}", borderwidth=2, relief="solid",
                         font="Times 13", bg="white").pack(pady=20)
                descrip_comprarLibro = """Esta es la funcionalidad que permite a los clientes comprar libros 
en el supermercado seleccionado anteriormente"""
                tk.Label(frame_zona2, text=descrip_comprarLibro, borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)

                mostraroferlibros(self.mercado.oferlibros)

            # Se definen los Widgets de la App

            # Menu de usuario
            barra_usuario = tk.Menu()

            menu_archivo = tk.Menu(barra_usuario, tearoff=False)
            menu_archivo.add_command(label="Aplicación", command=menBapli)
            menu_archivo.add_command(label="Salir", command=salirapp)
            barra_usuario.add_cascade(menu=menu_archivo, label="Archivo")

            menu_procesos = tk.Menu(barra_usuario, tearoff=False)
            menu_procesos.add_command(label="Identificar usuario", command=IdenUsuario)
            menu_procesos.add_command(label="Crear supermercado",
                                      command=crearsuper)  # Creo que es mejor dar la opcion de crear supermercado primero que la de seleccionar uno (Si algo lo cambiamos)
            menu_procesos.add_command(label="Seleccionar supermercado", command=selectsuper)
            menu_procesos.add_command(label="Comprar Libros", command=comprarLibro)
            # Aqui se agregarian las demas funcionalidades
            menu_procesos.add_command(label="Ver carrito de compras", command=finalizarCompra)
            barra_usuario.add_cascade(menu=menu_procesos, label="Procesos y Consultas")

            menu_ayuda = tk.Menu(barra_usuario, tearoff=False)
            menu_ayuda.add_command(label="Acerca de : ", command=acercade)
            barra_usuario.add_cascade(menu=menu_ayuda, label="Ayuda")

            ventana_usuario.config(menu=barra_usuario)

            ventana_usuario.mainloop()

        # Salir desde menu inicio
        def salir():
            inicio.destroy()

        # Boton descripcion del menu
        def descripcion():
            pop_up = tk.Toplevel()
            pop_up.title("Descripcion")
            pop_up.geometry("400x200+700+300")

            # Se crea la descripcion
            descrtext = """Este es un programa para la administración
    de tiendas de supermercado.\n\nEn este se incluye manejo de inventario y tipos de producto,
    también compra y venta de productos."""
            descrip = tk.Text(pop_up, wrap=tk.WORD, height=8)
            descrip.insert(tk.END, descrtext)

            # Boton para salir
            Bok = tk.Button(pop_up, text="OK", command=pop_up.destroy)

            # Se ponen los Widgets
            descrip.pack()
            Bok.pack()

        

        # bio Alejandro
        def mbioA():
            biografia_Text.delete("1.0", tk.END)
            bioA = "Nombre: Alejandro Ramírez Ramírez\nFecha de Nacimiento: 01/11/2001\nGustos: Leer, Dormir y jugar"
            biografia_Text.insert(tk.END, bioA)

        # bio Yiduar
        def mbioY():
            biografia_Text.delete("1.0", tk.END)
            bioY = "Nombre: Yiduar Duvier Rangel Quintero\nFecha/Lugar de nacimiento:06/09/2005 - Saravena Arauca" + "\nPresentación: Hago música"
            biografia_Text.insert(tk.END, bioY)

        # bio Santiago
        def mbioS():
            biografia_Text.delete("1.0", tk.END)
            bioS = "Nombre: Santiago Acevedo Cacua\nFecha de Nacimiento: 02/08/2004\nGustos: Caminar, comer y programar :D"
            biografia_Text.insert(tk.END, bioS)

        # bio Ruben
        def mbioR():
            biografia_Text.delete("1.0", tk.END)
            bioR = "Nombre: Ruben Urías Salas Higuita\nFecha/Lugar de nacimiento:2/09/1995 - Medellín Antioquia" + "\nPresentación:Apasionado por la tecnología, leer,la musica,Jugar video juegos y salir a conocer/aprender del mundo"
            biografia_Text.insert(tk.END, bioR)

        # cambiamos las imagenes de P4
        def cP4(event):
            imagenes = ["donpatacon.png", "carrefour.jpg", "D1.jpg", "exito.jpg", "ktronix.png", "walmart.jpg"]
            imagen_P4 = ImageTk.PhotoImage(Image.open(random.choice(imagenes)).resize((400, 400)))
            imagen_label_P4.configure(image=imagen_P4)
            imagen_label_P4.image = imagen_P4

        # Definimos la ventana de inicio

        inicio = tk.Tk()
        inicio.title("Gestor T")

        # Definimos los frames

        frame_izquierdo = tk.Frame(inicio, highlightbackground="black", highlightthickness=3)
        frame_derecho = tk.Frame(inicio, highlightbackground="black", highlightthickness=3)

        # Definimos los Widgets

        # Widget del menu inicio
        barra_menus = tk.Menu()
        menu_inicio = tk.Menu(barra_menus, tearoff=False)
        menu_inicio.add_command(label="Descripcion", command=descripcion)
        menu_inicio.add_command(label="Salir", command=salir)
        barra_menus.add_cascade(menu=menu_inicio, label="Inicio")
        inicio.config(menu=barra_menus)

        # Widgets del frame izquierdo

        # Widget mensaje de bienvenida
        bientext = """Bienvenido a nuestro gestor de supermercados Gestor T. 
        Esta herramienta te ayudará a administrar tus supermercados y 
        también será de utilidad para que tus clientes compren en ellos."""

        bienvenida_label = tk.Label(frame_izquierdo, text=bientext, borderwidth=2, relief="solid", font="Times 13",
                                    bg="white")

        # Widget imagen
        imagenes_P4 = ImageTk.PhotoImage(Image.open("donpatacon.png").resize((400, 400)))
        imagen_label_P4 = tk.Label(frame_izquierdo, image=imagenes_P4, relief="solid")
        imagen_label_P4.bind("<Enter>", cP4)

        # Boton ingresar

        ingresar = tk.Button(frame_izquierdo, text="Ingresar", relief="solid", width=10, command=ingresarapp,
                             font="Times 12 bold")

        # Widges del frame derecho

        # Widget biografia
        biografia_Text = tk.Text(frame_derecho, borderwidth=2, relief="solid", width=50, height=10,
                                 font="Times 15 bold")

        # Widget imagenes de los autores
        frame_imagenes = tk.Frame(frame_derecho)

        Aimg = ImageTk.PhotoImage(Image.open("Alejandro.jpg").resize((170, 170)))
        Aimg_Button = tk.Button(frame_imagenes, image=Aimg, relief="solid", command=mbioA)

        # Aqui va la foto de Santiago
        Simg = ImageTk.PhotoImage(Image.open("Santiago.jpg").resize((170, 170)))
        Simg_Button = tk.Button(frame_imagenes, image=Simg, relief="solid", command=mbioS)

        # Aqui va la foto de Ruben
        Rimg = ImageTk.PhotoImage(Image.open("Ruben.jpg").resize((170, 170)))
        Rimg_Button = tk.Button(frame_imagenes, image=Rimg, relief="solid", command=mbioR)

        # Posicionamos los Widgets

        frame_izquierdo.grid(row=0, column=0, padx=10)
        frame_derecho.grid(row=0, column=1)

        bienvenida_label.grid(row=0, column=0, pady=10, padx=5)
        biografia_Text.grid(row=0, column=0, padx=100, pady=20)

        imagen_label_P4.grid(row=1, column=0, pady=20)
        frame_imagenes.grid(row=1, column=0, pady=30)
        Aimg_Button.grid(row=0, column=0)
        Simg_Button.grid(row=0, column=1)
        Rimg_Button.grid(row=0, column=2)

        ingresar.grid(row=2, column=0, pady=10)

        inicio.mainloop()
        Serializador.serializarObjets(self.lista_super)


interfaz = Interfaz()
Interfaz.venInicio(interfaz)
