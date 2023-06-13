import tkinter as tk
from tkinter import messagebox
from PIL import ImageTk, Image
from FieldFrame import FieldFrame
from Cliente import Cliente
from Supermercado import Supermercado
from Libro import Libro
import random
from dataclasses import field


class Interfaz():
    
    def __init__(self):
        self.lista_libros=[]
        self.lista_carnicos=[]
        self.lista_no_carnicos=[]
        self.lista_tvs=[]
        self.lista_celulares=[]
        self.lista_super=[]
        self.cliente=Cliente()
        self.mercado=Supermercado()
    
    
    def venInicio(self):
        
        # Ingreso a la aplicacion
        def ingresarapp():
            
            inicio.destroy()
            ventana_usuario=tk.Tk()
            ventana_usuario.title("Gestor T")
            ventana_usuario.geometry("1700x800")
            
            #Se define el frame cambiante que permanece en la zona 2
            frame_zona2=tk.Frame(ventana_usuario)
            frame_zona2.pack()
            
            #Funcion para limpiar el frame
            #Esto debe ir en la linea inicial de cada proceso o consulta
            def limpia_frame():
                for widget in frame_zona2.winfo_children():
                    widget.destroy()
            
            #Se definen las funciones de los Widgets
            
            # Mensaje del boton acerca de
            def acercade():
                texto_acerca_de="Autores:\n\n-Alejandro Ramírez Ramírez\n\n-Rubén Urias Salas\n\n-Santiago Acevedo Cacua"
                messagebox.showinfo("Acerca de :",texto_acerca_de)
                
            # Mensaje del boton aplicacion    
            def menBapli():
                mensaje_bapli="""Esta aplicación le ayuda a gestionar sus supemermercados.\n
    También perminte a los clientes comprar en el supermercado de su preferencia."""
                messagebox.showinfo("Aplicacion",mensaje_bapli)
            
            # Boton salir de la ventan de usuario
            def salirapp():
                ventana_usuario.destroy()
                Interfaz.venInicio(self)
                
            #----------------- Funcionalidades y Procesos --------------
            
            # boton identificar usuario
            def IdenUsuario():
                limpia_frame()
                
                tk.Label(frame_zona2,text="Identificar Usuario",borderwidth=2,relief="solid",font="Times 13",bg="white").pack(pady=20)
                
                descrip_idenusuario="""Este proceso permite la identificación del usuario.\n
    Esto se hace por medio de sus datos personales básicos"""
                
                tk.Label(frame_zona2,text=descrip_idenusuario,borderwidth=2,relief="solid",font="Times 13",bg="white").pack(pady=20)
                
                identificadores=FieldFrame(frame_zona2,"Datos",["Nombre","Dirección","Saldo"],"Valor",None,None)
                identificadores.pack()
                
                # Acciones al oprimir el boton aceptar
                def aceptar():
                    
                    identificadores.valores=[x.get() for x in identificadores.lst_entrys]
                    
                    self.cliente.nombre=identificadores.getValue("Nombre")
                    self.cliente.direccion=identificadores.getValue("Dirección")
                    self.cliente.saldo=identificadores.getValue("Saldo")
                    
                    
                Aceptar=tk.Button(identificadores,text="Aceptar",font="Times 13",command=aceptar)
                Aceptar.grid(row=len(identificadores.criterios)+1,column=0,pady=10)
            
            #Boton crear supermercado
            def crearsuper():
                limpia_frame()
                
                def agregarprods():
                    
                    
                    def agregarLibro():
                        limpia_frame()
                        
                        
                            
                        tk.Label(frame_zona2,text=f"Agregar Libros a {self.mercado.nombre}",borderwidth=2,relief="solid",font="Times 13",bg="white").pack(pady=20)
                        tk.Label(frame_zona2,text="Aquí podrá agregar libros al supermercado seleccionado",borderwidth=2,relief="solid",font="Times 13",bg="white").pack(pady=20)
                        crilibros=["Titulo","Autor","Descripcion","ISBN","Precio","Cantidad"]
                        bookfield=FieldFrame(frame_zona2,"Datos del libro",crilibros,"Valores",None,None)
                        bookfield.pack()
                        
                        #Boton Aceptar
                        def agregarLib():
                            bookfield.valores=[x.get() for x in bookfield.lst_entrys]
                            
                            libro=Libro(bookfield.valores[0],bookfield.valores[1],bookfield.valores[2]
                                        ,bookfield.valores[3],bookfield.valores[4],bookfield.valores[5]
                                        ,self.mercado)
                            
                            self.mercado.oferlibros.append(libro)
                            
                            otro=messagebox.askyesno(message="¡Producto agregado con éxito!\n\n¿Desea agregar otro?", title="Producto")
                            
                            if otro:
                                agregarprods()
                            
                            print(self.mercado.oferlibros)
                                
                            
                        Aceptar=tk.Button(bookfield,text="Aceptar",font="Times 13",command=agregarLib)
                        Aceptar.grid(row=len(bookfield.criterios)+1,column=0,pady=10)
                        
                    
                    def agregarElectronica():
                        #Flujo para agregar un producto electrónico
                        pass
                    
                    def agregarComida():
                        #Flujo para agregar un producto comestible
                        pass
                    
                    #Obtiene los valores del frame anterior
                    if c_super.valores==None:
                        c_super.valores=[x.get() for x in c_super.lst_entrys]
                    self.mercado.nombre=c_super.getValue("Nombre")
                    self.lista_super.append(self.mercado)
                    
                    limpia_frame()
                    
                    tk.Label(frame_zona2,text="Agregar Productos",borderwidth=2,relief="solid",font="Times 13",bg="white").pack(pady=20)
                    tk.Label(frame_zona2,text=f"¿Que productos deseas agregar a {self.mercado.nombre}",borderwidth=2,relief="solid",font="Times 13",bg="white").pack(pady=20)
                    
                    #Botones para agregar diferentes tipos de producto
                    tk.Button(frame_zona2,text="Libros",font="Times 13",command=agregarLibro).pack(pady=10)
                    tk.Button(frame_zona2,text="Electronica",font="Times 13").pack(pady=10)
                    tk.Button(frame_zona2,text="Comida",font="Times 13",command=agregarLibro).pack(pady=10)
                    
                
                tk.Label(frame_zona2,text="Crear Supermercado",borderwidth=2,relief="solid",font="Times 13",bg="white").pack(pady=20)
                descrip_crearsup="Este proceso le permite crear un nuevo supermercado y agregar los productos que necesita"
                tk.Label(frame_zona2,text=descrip_crearsup,borderwidth=2,relief="solid",font="Times 13",bg="white").pack(pady=20)
                c_super=FieldFrame(frame_zona2,"Nuevo Supermercado",["Nombre"],"Ingrese el nombre del nuevo supermercado",None,None)
                c_super.pack()
                
                Aceptar=tk.Button(c_super,text="Aceptar",font="Times 13",command=agregarprods)
                Aceptar.grid(row=len(c_super.criterios)+1,column=0,pady=10)
                
            
            #Boton seleccionar supermercado
            def selectsuper():
                
                limpia_frame()
                
                # funcion de los botones del listbox
                def listboxselection(event):
                    selected_item=listbox.get(listbox.curselection())
                    
                    if selected_item=="Agregar Supermercado":
                        crearsuper()
                    else:
                        for s in self.lista_super:
                            if s.nombre==selected_item:
                                self.mercado=s
                                messagebox.showinfo("Selección", f"Has seleccionado {selected_item}")
                                break
                
                tk.Label(frame_zona2,text="Seleccionar Supermercado",borderwidth=2,relief="solid",font="Times 13",bg="white").pack(pady=20)
                
                descrip_selectsuper="""Este proceso permite elegir el supermercado en el cual se desea comprar.\n
También, permite agregar un nuevo supermercado al listado"""
                
                descrip_selectsuperindi="Seleccione el supermercado en el cual desea comprar: "
                
                tk.Label(frame_zona2,text=descrip_selectsuper,borderwidth=2,relief="solid",font="Times 13",bg="white").pack(pady=20)
                
                tk.Label(frame_zona2,text=descrip_selectsuperindi,borderwidth=2,relief="solid",font="Times 13",bg="white",fg="red").pack(pady=20)
                
                listbox=tk.Listbox(frame_zona2,borderwidth=2,relief="solid",font="Times 13",bg="white")
                
                if not self.lista_super==[]:
                    for s in self.lista_super:
                        listbox.insert(tk.END,s.nombre)
                        
                listbox.insert(tk.END,"Agregar Supermercado")
                listbox.pack()
                    
                listbox.bind('<<ListboxSelect>>', listboxselection)
                
                
            #Se definen los Widgets de la App
            
            #Menu de usuario
            barra_usuario=tk.Menu()
            
            menu_archivo=tk.Menu(barra_usuario,tearoff=False)
            menu_archivo.add_command(label="Aplicación",command=menBapli)
            menu_archivo.add_command(label="Salir",command=salirapp)
            barra_usuario.add_cascade(menu=menu_archivo,label="Archivo")
            
            menu_procesos=tk.Menu(barra_usuario,tearoff=False)
            menu_procesos.add_command(label="Identificar usuario",command=IdenUsuario)
            menu_procesos.add_command(label="Seleccionar supermercado",command=selectsuper)
            menu_procesos.add_command(label="Crear supermercado",command=crearsuper)
            menu_procesos.add_command(label="Comprar Libros")
            #Aqui se agregarian las demas funcionalidades
            menu_procesos.add_command(label="Ver carrito de compras")
            barra_usuario.add_cascade(menu=menu_procesos,label="Procesos y Consultas")
            
            
            menu_ayuda=tk.Menu(barra_usuario,tearoff=False)
            menu_ayuda.add_command(label="Acerca de : ",command=acercade)
            barra_usuario.add_cascade(menu=menu_ayuda,label="Ayuda")    
            
            ventana_usuario.config(menu=barra_usuario)
            
            ventana_usuario.mainloop()
        
        # Salir desde menu inicio
        def salir():
            inicio.destroy()
        
        #Boton descripcion del menu
        def descripcion():
            pop_up=tk.Toplevel()
            pop_up.title("Descripcion")
            pop_up.geometry("400x200+700+300")
            
            #Se crea la descripcion
            descrtext="""Este es un programa para la administración
    de tiendas de supermercado.\n\nEn este se incluye manejo de inventario y tipos de producto,
    también compra y venta de productos."""
            descrip=tk.Text(pop_up,wrap=tk.WORD,height=8)
            descrip.insert(tk.END,descrtext)
            
            #Boton para salir
            Bok=tk.Button(pop_up,text="OK",command=pop_up.destroy)
            
            #Se ponen los Widgets
            descrip.pack()
            Bok.pack()
        
        #bio Alejandro
        def mbioA():
            biografia_Text.delete("1.0",tk.END)
            bioA="Nombre: Alejandro Ramírez Ramírez\nFecha de Nacimiento: 01/11/2001\nGustos:Leer, Dormir y jugar"
            biografia_Text.insert(tk.END,bioA)
        #bio Santiago
        def mbioS():
            biografia_Text.delete("1.0",tk.END)
            bioS=""#poner biografia
            biografia_Text.insert(tk.END,bioS)
        #bio Ruben
        def mbioR():
            biografia_Text.delete("1.0",tk.END)
            bioR=""#poner biografia
            biografia_Text.insert(tk.END,bioR)
            
        #cambiamos las imagenes de P4
        def cP4(event):
            imagenes=["donpatacon.png","carrefour.jpg","D1.jpg","exito.jpg","ktronix.png","walmart.jpg"]
            imagen_P4=ImageTk.PhotoImage(Image.open(random.choice(imagenes)).resize((600,600)))
            imagen_label_P4.configure(image=imagen_P4)
            imagen_label_P4.image = imagen_P4
    
        #Definimos la ventana de inicio
        
        inicio=tk.Tk()
        inicio.title("Gestor T")
        inicio.geometry("1700x800")
            
        #Definimos los frames
        
        frame_izquierdo=tk.Frame(inicio,highlightbackground="black",highlightthickness=3)
        frame_derecho=tk.Frame(inicio,highlightbackground="black",highlightthickness=3)
        
        #Definimos los Widgets
        
        #Widget del menu inicio
        barra_menus=tk.Menu()
        menu_inicio=tk.Menu(barra_menus,tearoff=False)
        menu_inicio.add_command(label="Descripcion",command=descripcion)
        menu_inicio.add_command(label="Salir",command=salir)
        barra_menus.add_cascade(menu=menu_inicio,label="Inicio")
        inicio.config(menu=barra_menus)
        
        
        #Widgets del frame izquierdo
        
        #Widget mensaje de bienvenida
        bientext="""Bienvenido a nuestro gestor de supermercados Gestor T. Esta herramienta te ayudará a administrar tus supermercados y 
        también será de utilidad para que tus clientes compren en ellos."""
        
        bienvenida_label=tk.Label(frame_izquierdo,text=bientext,borderwidth=2,relief="solid",font="Times 13",bg="white")
        
        
        #Widget imagen
        imagenes_P4=ImageTk.PhotoImage(Image.open("donpatacon.png").resize((600,600)))
        imagen_label_P4=tk.Label(frame_izquierdo,image=imagenes_P4,relief="solid")
        imagen_label_P4.bind("<Enter>",cP4)
        
        #Boton ingresar
        
        ingresar=tk.Button(frame_izquierdo,text="Ingresar",relief="solid", width = 10, command=ingresarapp , font="Times 12 bold")
        
        
        #Widges del frame derecho
        
        #Widget biografia
        biografia_Text=tk.Text(frame_derecho,borderwidth=2,relief="solid",width=50,height=10,font="Times 15 bold")
        
        
        #Widget imagenes de los autores
        frame_imagenes=tk.Frame(frame_derecho)
        
        Aimg=ImageTk.PhotoImage(Image.open("Alejandro.jpg").resize((270,270)))
        Aimg_Button=tk.Button(frame_imagenes,image=Aimg,relief="solid",command=mbioA)
        
        # Aqui va la foto de Santiago
        Simg=ImageTk.PhotoImage(Image.open("frente1.jpg").resize((270,270)))
        Simg_Button=tk.Button(frame_imagenes,image=Simg,relief="solid",command=mbioS)
        
        # Aqui va la foto de Ruben
        Rimg=ImageTk.PhotoImage(Image.open("frente2.jpg").resize((270,270)))
        Rimg_Button=tk.Button(frame_imagenes,image=Rimg,relief="solid",command=mbioR)
        
        
        
        #Posicionamos los Widgets
        
        frame_izquierdo.grid(row=0,column=0,padx=10)
        frame_derecho.grid(row=0,column=1)
        
        bienvenida_label.grid(row=0,column=0,pady=10,padx=5)
        biografia_Text.grid(row=0,column=0,padx=100,pady=20)
        
        imagen_label_P4.grid(row=1,column=0,pady=20)
        frame_imagenes.grid(row=1,column=0,pady=30)
        Aimg_Button.grid(row=0,column=0)
        Simg_Button.grid(row=0,column=1)
        Rimg_Button.grid(row=0,column=2)
        
        ingresar.grid(row=2,column=0,pady=10)
        
        inicio.mainloop()

interfaz=Interfaz()
Interfaz.venInicio(interfaz)