import tkinter as tk
from PIL import ImageTk, Image
import random

#Definimos la ventana de inicio

inicio=tk.Tk()
inicio.title("Gestor T")
inicio.geometry("1700x800")

#Definimos las funciones para los Widgets

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
    bioA="Nombre: Alejandro Ramírez Ramírez\nFecha de Nacimiento: 01/11/2001\nGustos:Leer,Dormir y jugar"
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

ingresar=tk.Button(frame_izquierdo,text="Ingresar",relief="solid", width = 10, font="Times 12 bold")


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