import tkinter as tk
from tkinter import Frame

class FieldFrame(Frame):
    
    def __init__(self,master,tituloCriterios,criterios,tituloValores,valores,habilitado):
        
        super().__init__(master)
        
        
        self.tituloCriterios=tituloCriterios
        self.criterios=criterios
        self.tituloValores=tituloValores
        self.valores=valores
        self.habilitado=habilitado
        self.master=master
        
        self.lst_entrys=[]

        #Se definen los Widgets
        
        # Titulo de la columna de los criterios
        tk.Label(self,text=tituloCriterios,borderwidth=2,relief="solid",font="Times 13",bg="white").grid(row=0,column=0,pady=10,padx=10)
        
        # Titulo de la columna de los valores
        tk.Label(self,text=tituloValores,borderwidth=2,relief="solid",font="Times 13",bg="white").grid(row=0,column=1,pady=10,padx=10)
        
        # Labels y Entrys de los criterios
        if not valores==None:
            for i in range(len(criterios)):
                tk.Label(self,text=criterios[i]).grid(row=i+1,column=0)
                tk.Entry(self,text=valores[i]).grid(row=i+1,column=1)
        else:
            for i in range(len(criterios)):
                tk.Label(self,text=criterios[i],font="Times 13").grid(row=i+1,column=0)
                en=tk.Entry(self,width=50)
                self.lst_entrys.append(en)
                en.grid(row=i+1,column=1)
                
        #Se definen las funciones de los botones
        
        #Recoger los valores de los Entrys al oprimir aceptar
        def aceptar():
            self.valores=[x.get() for x in self.lst_entrys]
            
        #Borrar contenido de las Entrys
        def borrar():
            for x in self.lst_entrys:
                x.delete(0,tk.END)
            
        
        
        # Boton borrar
        Borrar=tk.Button(self,text="Borrar",font="Times 13",command=borrar).grid(row=i+2,column=1,pady=10)
            
            
            

    
    def getValue(self,criterio):
        return self.valores[self.criterios.index(criterio)]