
from ErrorAplicacion import *
import tkinter as tk
import regex as re
from tkinter import messagebox
from PIL import ImageTk, Image


from Celular import Celular
from FieldFrame import FieldFrame
from Cliente import Cliente
from Supermercado import Supermercado
from Libro import Libro
import random

from Tv import Tv

from Carne import Carne
from noCarnicos import noCarnicos


class Interfaz():
    def __init__(self):
        self.lista_libros = []
        self.lista_carnicos = []
        self.lista_no_carnicos = []
        self.lista_tvs = []
        self.lista_celulares = []
        self.lista_super = []
        self.cliente = Cliente()
        self.mercado = None
        self.filtrolibro=0

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
                     "\n\n\n- Al finalizar todas tus compras (libros, comida, componentes electronicos) debes dirigirte al carrito de compra y concretar tu pedido"
                     "\n\n\n\n ¡Espero que disfrutes usando nuestro gestor de tiendas!")
            pasos_uso_zona2.grid(row=2, pady=30, ipadx=10, ipady=20)

            # Funcion para limpiar el frame
            # Esto debe ir en la linea inicial de cada proceso o consulta
            def limpia_frame():
                for widget in frame_zona2.winfo_children():
                    widget.destroy()

            # Se definen las funciones de los Widgets

            # Mensaje del boton acerca de
            def acercade():
                texto_acerca_de = "Autores:\n\n-Alejandro Ramírez Ramírez\n\n-Rubén Urias Salas\n\n-Santiago Acevedo Cacua"
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

                tk.Label(frame_zona2, text="Carrito de compras", borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)

                # Completar------------------

            # boton identificar usuario
            def IdenUsuario():
                limpia_frame()

                tk.Label(frame_zona2, text="Identificar Usuario", borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)

                descrip_idenusuario = """Este proceso permite la identificación del usuario.\nEsto se hace por medio de sus datos personales básicos"""

                tk.Label(frame_zona2, text=descrip_idenusuario, borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)

                identificadores = FieldFrame(frame_zona2, "Datos", ["Nombre", "Dirección", "Saldo"], "Valor", None,
                                             None)
                identificadores.pack()

                # Acciones al oprimir el boton aceptar
                def aceptar():
                    identificadores.valores = [x.get() for x in identificadores.lst_entrys]

                    self.cliente.nombre = identificadores.getValue("Nombre")
                    self.cliente.direccion = identificadores.getValue("Dirección")
                    self.cliente.saldo = identificadores.getValue("Saldo")

                Aceptar = tk.Button(identificadores, text="Aceptar", font="Times 13", command=aceptar)
                Aceptar.grid(row=len(identificadores.criterios) + 1, column=0, pady=10)

            # Boton crear supermercado
            c_super = "" #Esto es para poder reutilizar el codigo de crear productos más adelante
            def crearsuper():
                limpia_frame()
                global c_super


                tk.Label(frame_zona2, text="Crear Supermercado", borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)
                descrip_crearsup = "Este proceso le permite crear un nuevo supermercado y agregar los productos que necesita"
                tk.Label(frame_zona2, text=descrip_crearsup, borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)
                c_super = FieldFrame(frame_zona2, "Nuevo Supermercado", ["Nombre"],
                                     "Ingrese el nombre del nuevo supermercado", None, None)
                c_super.pack()

                Aceptar = tk.Button(c_super, text="Aceptar", font="Times 13", command=agregarprods)
                Aceptar.grid(row=len(c_super.criterios) + 1, column=0, pady=10)

            def agregarprods():
                global c_super

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
                                if re.match(r"\D",bookfield.valores[0])==None:
                                    buentipo=[crilibros[0],"palabras/letras"]
                                    0/0
                                if re.match(r"\D",bookfield.valores[1])==None:
                                    buentipo=[crilibros[1],"palabras/letras"]
                                    0/0
                                if re.match(r"\D",bookfield.valores[2])==None:
                                    buentipo=[crilibros[2],"palabras/letras"]
                                    0/0
                                buentipo=[crilibros[3],"un numero"]
                                int(bookfield.valores[3])
                                buentipo=[crilibros[4],"un numero"]
                                int(bookfield.valores[4])
                                buentipo=[crilibros[5],"un numero"]
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
                                raise dtipoInvalido(buentipo[0],buentipo[1])

                    Aceptar = tk.Button(bookfield, text="Aceptar", font="Times 13", command=agregarLib)
                    Aceptar.grid(row=len(bookfield.criterios) + 1, column=0, pady=10)            

                # Obtiene los valores del frame anterior
                if c_super.valores is None:
                    c_super.valores = [x.get() for x in c_super.lst_entrys]
                self.mercado=Supermercado(c_super.getValue("Nombre"))
                self.lista_super.append(self.mercado)

                limpia_frame()

                tk.Label(frame_zona2, text="Agregar Productos", borderwidth=2, relief="solid", font="Times 13",
                         bg="white").pack(pady=20)
                tk.Label(frame_zona2, text=f"¿Que productos deseas agregar al Supermercado {self.mercado.nombre}", borderwidth=2,
                         relief="solid", font="Times 13", bg="white").pack(pady=20)

                # Botones para agregar diferentes tipos de producto
                tk.Button(frame_zona2, text="Libros", font="Times 13", command=agregarLibro).pack(pady=10)
                tk.Button(frame_zona2, text="Electronica", font="Times 13", command=agregarElectronica).pack(
                    pady=10)
                tk.Button(frame_zona2, text="Alimentos", font="Times 13", command=AgregarAlimentos).pack(pady=10)
            
                # Flujo para agregar Alimentos
            def agregarCarnico():
                limpia_frame()

                tk.Label(frame_zona2, text=f"Agregar Alimentos Carnicos al Supermercado {self.mercado.nombre}", borderwidth=2,
                         relief="solid", font="Times 13", bg="white").pack(pady=20)
                tk.Label(frame_zona2, text="Aquí podrá agregar Alimentos Carnicos al Supermercado seleccionado",
                         borderwidth=2, relief="solid", font="Times 13", bg="white").pack(pady=20)
                cricarne = ["Nombre", "Precio por Libra", "Tipo de Producto", "Peso en Libras", "Unidades que desea añadir"]
                carnefield = FieldFrame(frame_zona2, "Datos del producto Carncio", cricarne, "Valores", None, None)
                carnefield.pack()

                # Boton Aceptar
                def agregarCarne():
                    carnefield.valores = [x.get() for x in carnefield.lst_entrys]

                    carne = Carne(carnefield.valores[0], carnefield.valores[1], carnefield.valores[2]
                            ,carnefield.valores[3], carnefield.valores[4], self.mercado)

                    self.mercado.ofercarne.append(carne)

                    otro = messagebox.askyesno(
                        message="¡Producto agregado con éxito!\n\n¿Desea agregar un producto diferente?",
                        title="Producto")

                    if otro:
                        agregarprods()
                    else:
                        crearsuper()
                
                aceptar = tk.Button(carnefield, text="Aceptar", font="Times 13", command=agregarCarne)
                aceptar.grid(row=len(carnefield.criterios) + 1, column=0, pady=10)


            def agregarnoCarnicos():
                limpia_frame()

                tk.Label(frame_zona2, text=f"Agregar Alimentos noCarnicos al Supermercado {self.mercado.nombre}", borderwidth=2,
                         relief="solid", font="Times 13", bg="white").pack(pady=20)
                tk.Label(frame_zona2, text="Aquí podrá agregar Alimentos noCarnicos al Supermercado seleccionado",
                         borderwidth=2, relief="solid", font="Times 13", bg="white").pack(pady=20)

                crinocarnes = ["Nombre", "Precio","Categoria:1.Granos / 2.Lacteos / 3.Verduras / 4.Otros", "Cantidad unidades que desea añadir"]
                nocarnefield = FieldFrame(frame_zona2, "Datos del producto noCarncio", crinocarnes, "Valores", None, None)
                nocarnefield.pack()

                # Boton Aceptar
                def agregarnoCarne():
                    nocarnefield.valores = [x.get() for x in nocarnefield.lst_entrys]

                    nocarne = noCarnicos(nocarnefield.valores[0], nocarnefield.valores[1], nocarnefield.valores[2]
                            ,nocarnefield.valores[3], self.mercado)

                    self.mercado.ofernocarnicos.append(nocarne)

                    otro = messagebox.askyesno(
                        message="¡Producto agregado con éxito!\n\n¿Desea agregar un producto diferente?",
                        title="Producto")

                    if otro:
                        agregarprods()
                    else:
                        crearsuper()
                
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

            # Funcionalidad Alimentos                                                                                          
            def MenuAlimentos():
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

                tk.Button(frame_zona2, text="!COMPRAR SOLO ALIMENTOS CARNICOS", font="Times 13", command=ofertaCarnicos).grid(row=3,
                                                                                                     column=0,
                                                                                                     pady=10)

                tk.Button(frame_zona2, text="!COMPRAR SOLO ALIMENTOS noCARNICOS", font="Times 13", command=agregarnoCarnicos).grid(row=4,
                                                                                                     column=0,
                                                                                                     pady=15)

                tk.Button(frame_zona2, text="!COMPRAR ALIMENTOS CARNICOS Y noCARNICOS", font="Times 13", command=agregarnoCarnicos).grid(row=5,
                                                                                                     column=0,
                                                                                                     pady=15)                                                                                     

            def ofertaCarnicos():
                limpia_frame()
                if (len(self.mercado.ofercarne) == 0):
                    tk.Label(frame_zona2, text="Esta seccion no tiene productos \n\n:(\n\n¿Desea ingresar un producto?",
                            borderwidth=2, relief="solid", font="Times 14", bg="white").grid(pady=30, ipadx=16,ipady=15,columnspan=3)
                    tk.Button(frame_zona2, text="Si", font=("Times 13", 16), command = agregarCarnico).grid(row=1, column=2, pady = 20) 
                    tk.Button(frame_zona2, text="No, ir a otra Sección", font=("Times 13", 16), command = ofertaProductos).grid(row=2, column=2, pady = 20)
                else:
                    # funcion de los botones del listbox
                    def listboxselectCarne(event):
                       
                        selected_item = self.mercado.ofercarne[int(listbox.get(listbox.curselection())[0]) - 1]

                        tk.Label(frame_zona2, text= ComprarAlimento(selected_item, 1), borderwidth=2, relief="solid", font="Times 13",
                                 bg="white").pack(pady=20)

                    #Presentación de bienvenida
                    tk.Label(frame_zona2, text="Bienvenido al Area de Carnes del Supermercado\n\n*** !CARNES EN OFERTA!***\n\nAcontinuacion nuestros productos disponibles",
                            borderwidth=2, relief="solid", font="Times 14", bg="white").grid(pady=20, ipadx=5,ipady=5,columnspan=5, row = 0)

                    #Se crea el listbox para mostrar los productos
                    listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13", bg="white")

                    listbox.grid(row=1, column=2)  

                    if not self.mercado.ofercarne == []:
                        for l in range(len(self.mercado.ofercarne)):
                            listbox.insert(tk.END, str(l + 1) + ". " + self.mercado.ofercarne[l].nombre)

                    listbox.grid()

                    listbox.bind('<<ListboxSelect>>', listboxselectCarne)

                    #Se muestra las opciones adicionales de filtros e ir a otras secciones
                    tk.Label(frame_zona2, text="***Opciones adcionales***",
                            borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2, row = 2, column=2)

                    tk.Button(frame_zona2, text="Filtrar por tipo de carne", font=("Times 12", 10), command = lambda: filtrosAliementos(1)).grid(row=3, column=2, pady = 8) 
                    tk.Button(frame_zona2, text="Volver al menu Alimentos", font=("Times 12", 10), command = MenuAlimentos).grid(row=4, column=2, pady = 8)
                    tk.Button(frame_zona2, text="Escoger otra Seccion", font=("Times 12", 10), command = ofertaProductos).grid(row=5, column=2, pady = 8)


            def filtrosAliementos(fil_Alim):
                limpia_frame()

                if fil_Alim == 1:#filtro por tipo de carne        

                    tk.Label(frame_zona2, text=f"***Bienvenido al filtro por tipo de Carne***"
                     f"\nEstas son las Carnes disponibles\nSeleccione el tipo de carne que desea filtrar",
                            borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2, row = 1, column=2)
                                                            
                    tipoCarne = Carne.listaTipos(self.mercado.ofercarne)

                      #Se define el metodo para mostrar la listabox
                    def listbox_filtroCarne(event):

                        selected_item = tipoCarne[int(listbox_f.get(listbox_f.curselection())[0]) - 1]

                        #Comprar el alimento escogido
                        tk.Label(frame_zona2, text=mostrar_filtro(selected_item), borderwidth=2, relief="solid", font="Times 13",
                                 bg="white").grid(pady=20)

                    #Se crea el listbox para mostrar los productos
                    listbox_f= tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13", bg="white")

                    listbox_f.grid(row=2, column=2)  

                    if not tipoCarne == []:
                        for k in range(len(tipoCarne)):
                            listbox_f.insert(tk.END, str(k + 1) + ". " +tipoCarne[k])

                    listbox_f.grid()

                    listbox_f.bind('<<ListboxSelect>>', listbox_filtroCarne)

                    def mostrar_filtro(selected_item):

                        def lista_filtro(event):
                        
                            selected_item2 = self.mercado.ofercarne[int(listbox_f.get(listbox_f.curselection())[0]) - 1]

                            #Comprar el alimento escogido
                            tk.Label(frame_zona2, text = ComprarAlimento(selected_item2,1), borderwidth=2, relief="solid", font="Times 13",
                                 bg="white").grid(pady=20)

                        #Se crea el listbox para mostrar los productos
                        listbox_f= tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13", bg="white")
                        listbox_f.grid(row=5, column=2)

                        if not self.mercado.ofercarne == []:
                            for j in range(len(self.mercado.ofercarne)):
                                if self.mercado.ofercarne[j].tipo == selected_item:
                                    listbox_f.insert(tk.END, str(j + 1) + ". " +self.mercado.ofercarne[j].nombre)

                        listbox_f.grid()

                        listbox_f.bind('<<ListboxSelect>>', lista_filtro)

                        tk.Label(frame_zona2, text=f"**Escoja la oferta que desee comprar**\nEl resultado para la carne {selected_item} es",
                            borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2, row = 4, column=2)


            def ComprarAlimento(objeto, eleccion):
                limpia_frame()

                tk.Label(frame_zona2, text="SECCIÓN DE COMPRAS DE ALIMENTOS",
                            borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2,columnspan=5)

                if eleccion == 1:#comprar Carne
                    tk.Label(frame_zona2, text=f"Has seleccionado la Oferta {objeto.nombre}",
                            borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2, columnspan=5)

                    tk.Label(frame_zona2, text=f"Nombre: {objeto.nombre}\nPrecio por libra: {objeto.precio}\nTipo de carne: {objeto.tipo}"
                    f"\nLibras por unidad: {objeto.pesolibra}\nUnidades en Stock: {objeto.cantidad}",
                            borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=8, ipadx=8, ipady=2, columnspan=5)

                    tk.Button(frame_zona2, text="Agregar al carrito de compras", font=("Times 12", 10), command = finalizarCompra).grid(row=3, column=2, pady = 10)

                    tk.Button(frame_zona2, text="Escoger otra Seccion", font=("Times 12", 10), command = ofertaProductos).grid(row=4, column=2, pady = 10)

                    tk.Button(frame_zona2, text="Volver", font=("Times 12", 10), command = ofertaCarnicos).grid(row=5, column=2, pady = 10)
                
                    
                    print("Es usted menor de edad.")
                elif eleccion == 2:#Comprar noCarnicos
                    
                    print("Es usted menor de edad.")
                elif eleccion == 3:#Comprar Carnicos y noCarnicos
                    
                    print("Es usted menor de edad.")


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

                    tv = Tv(tvfield.valores[0], tvfield.valores[1], tvfield.valores[2]
                            , tvfield.valores[3], tvfield.valores[4], tvfield.valores[5]
                            , self.mercado)

                    self.mercado.ofercarne.append(tv)

                    otro = messagebox.askyesno(
                        message="¡Producto agregado con éxito!\n\n¿Desea agregar un producto diferente?",
                        title="Producto")

                    if otro:
                        agregarprods()
                    else:
                        crearsuper()

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
                    limpia_frame()
                    tk.Label(frame_zona2, text="¿Que tipo de producto electronico deseas?", borderwidth=2,
                             relief="solid", font="Times 13", bg="white").grid(pady=30, ipadx=15, ipady=10, columnspan=2)

                    def ofertaCelulares():
                        limpia_frame()
                        if (len(self.mercado.ofercelular) == 0):
                            tk.Label(frame_zona2, text="Este supermercado no tiene celulares :(\n¿Deseas crear uno?",
                                     borderwidth=2, relief="solid", font="Times 13", bg="white").grid(pady=30, ipadx=15,ipady=10,columnspan=2)
                            tk.Button(frame_zona2, text="Si", font=("Times 13", 16), command=agregarCelular).grid(row=1, column=0)  # Creo que si lo pude implementar
                            tk.Button(frame_zona2, text="No", font=("Times 13", 16)).grid(row=1, column=1)
                        else:
                            # funcion de los botones del listbox
                            def listboxselectcel(event):
                                selected_item = listbox.get(listbox.curselection())

                                for s in self.mercado.ofercelular:
                                    if s.nombre == selected_item:
                                        messagebox.showinfo("Selección", f"Has seleccionado {selected_item}")
                                        # Este metodo nos muestra que products hay en el supermercado seleccionado
                                        break

                            tk.Label(frame_zona2, text="Seleccionar Celular", borderwidth=2, relief="solid",
                                     font="Times 13",
                                     bg="white").pack(pady=20)

                            descrip_selectsuper = """Elige cual es el celular que deseas comprar."""

                            descrip_selectsuperindi = "Selecciona el celular que deseas comprar: "

                            tk.Label(frame_zona2, text=descrip_selectsuper, borderwidth=2, relief="solid",
                                     font="Times 13",
                                     bg="white").pack(pady=20)

                            tk.Label(frame_zona2, text=descrip_selectsuperindi, borderwidth=2, relief="solid",
                                     font="Times 13",
                                     bg="white", fg="red").pack(pady=20)

                            listbox = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13",
                                                 bg="white")

                            if not self.mercado.ofercelular == []:
                                for s in self.mercado.ofercelular:
                                    listbox.insert(tk.END, s.nombre)

                            listbox.pack()

                            listbox.bind('<<ListboxSelect>>', listboxselectcel)

                    tk.Button(frame_zona2, text="Celular", font=("Times 13", 16), command=ofertaCelulares).grid(column=0, row=1)
                    tk.Button(frame_zona2, text="Televisor", font=("Times 13", 16)).grid(column=1, row=1)

                tk.Button(frame_zona2, text="Libros", font="Times 13", command=comprarLibro).grid(pady=15)
                tk.Button(frame_zona2, text="Electronios", font="Times 13", command=ofertaElectronico).grid(pady=15)
                tk.Button(frame_zona2, text="Alimentos", font="Times 13", command= MenuAlimentos).grid(pady=15)

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

            # Funcionalidad comprarLibro
            def comprarLibro():
                
                if self.mercado.nombre==None:
                    raise comprarSinEligirSup(selectsuper)
                if self.cliente.nombre==None:
                    raise comprarSinUsuario(IdenUsuario)
                    
                limpia_frame()
                self.filtrolibro=0
                
                def mostraroferlibros(oferlibro):
                    limpia_frame()
                    oferlibros = oferlibro.copy()
                    
                    def borrarFiltros():
                        self.filtrolibro=0
                        mostraroferlibros(self.mercado.oferlibros)
                    
                    #Filtro #1
                    def filtroAutor():
                        autores = Libro.listaAutores(oferlibros)

                        limpia_frame()

                        def selectAutor(event):
                            selected_item = oferlibros[int(listboxAutores.get(listboxAutores.curselection())[0]) - 1]
                            self.filtrolibro=1
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
                        
                    #Filtro 2
                    def filtroPrecio():
                        limpia_frame()
                        
                        def defrango():
                            precioField.valores=[x.get() for x in precioField.lst_entrys]
                            self.filtrolibro=2
                            mostraroferlibros(Libro.filtrarporPrecio(oferlibros, precioField.valores[0]
                                                                     , precioField.valores[1]))
                            
                            
                        tk.Label(frame_zona2, text="Filtrar por precio", borderwidth=2, relief="solid", font="Times 13",
                                 bg="white").pack(pady=20)
                                 
                        tk.Label(frame_zona2, text="Aquí podrás filtrar los libros en un rango de precio", borderwidth=2, relief="solid", font="Times 13",
                                 bg="white").pack(pady=20)
                                 
                        precioField=FieldFrame(frame_zona2, "Precios", ["Precio Mínimo","Precio Máximo"], "Valores", None, None)
                        
                        Aceptar = tk.Button(precioField, text="Aceptar", font="Times 13", command=defrango)
                        Aceptar.grid(row=len(precioField.criterios) + 1, column=0, pady=10)
                        
                        
                                 
                        
                        
                    def selectlibro(event):

                        selected_item = oferlibros[int(listbox_libros.get(listbox_libros.curselection())[0]) - 1]

                        limpia_frame()

                        def agregarlibalcarro():
                            self.cliente.carrito.append(selected_item)
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

                        tk.Button(frame_zona2, text="Agregar al carrito", font="Times 13",command=agregarlibalcarro).pack(pady=10)
                        tk.Button(frame_zona2, text="Volver", font="Times 13", command=comprarLibro).pack()

                    L_antesdelistbox = tk.Label(frame_zona2, text="Estos son los libros que tenemos disponibles",
                                                borderwidth=2, relief="solid", font="Times 13", bg="white")
                    L_antesdelistbox.pack(pady=20)

                    listbox_libros = tk.Listbox(frame_zona2, borderwidth=2, relief="solid", font="Times 13", bg="white")

                    for l in range(len(oferlibros)):
                        listbox_libros.insert(tk.END, str(l + 1) + ". " + oferlibros[l].titulo)

                    listbox_libros.pack()

                    tk.Label(frame_zona2, text="Filtros", borderwidth=2, relief="solid", font="Times 13",
                             bg="white").pack(pady=10)

                    tk.Button(frame_zona2, text="Autor", font="Times 13", command=filtroAutor).pack(pady=10)
                    tk.Button(frame_zona2, text="Precio", font="Times 13").pack(pady=10)
                    
                    
                    
                    if self.filtrolibro!=0:
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
            menu_procesos.add_command(label="Ver carrito de compras")
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

        # bio Santiago
        def mbioS():
            biografia_Text.delete("1.0", tk.END)
            bioS = "Nombre: Santiago Acevedo Cacua\nFecha de Nacimiento: 02/08/2004\nGustos: Caminar, comer y programar :D"
            biografia_Text.insert(tk.END, bioS)

        # bio Ruben
        def mbioR():
            biografia_Text.delete("1.0", tk.END)
            bioR="Nombre: Ruben Urías Salas Higuita\nFecha/Lugar de nacimiento:2/09/1995 - Medellín Antioquia"+"\nPresentación:Apasionado por la tecnología, leer,la musica,Jugar video juegos y salir a conocer/aprender del mundo"
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


interfaz = Interfaz()
Interfaz.venInicio(interfaz)
