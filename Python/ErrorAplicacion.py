
from tkinter import messagebox
import tkinter as tk

class ErrorAplicacion(Exception):
    def __init__(self,msg_C1C2,msg_EI):
        super().__init__(f"\n\nManejo de errores de la Aplicación: {msg_C1C2}.\n\n{msg_EI}")

class ErrorLogico(ErrorAplicacion):
    def __init__(self,msg_EIC1):
        super().__init__("Ha ocurrido un error con los valores de entrada del Field",msg_EIC1)
        
class ErrorOrden(ErrorAplicacion):
    def __init__(self,msg_EIC2):
        super().__init__("Ha ocurrido un error con el orden de procesos",msg_EIC2)

#Las que inventamos 
class comprarSinEligirSup(ErrorOrden):
    def __init__(self,selectsuper):
        super().__init__("Error: No se ha seleccionado un supermercado en el cual comprar")
        messagebox.showerror("Error", "¡Debes elegir un supermercado para comprar!")
        selectsuper()
        
class comprarSinUsuario(ErrorOrden):
    def __init__(self,IdenUsuario):
        super().__init__("Error: No se registrado ningún usuario")
        messagebox.showerror("Error", "¡No se ha registrado ningún usuario!")
        IdenUsuario()

#Falta aplicar este
class cantidadMaxima(ErrorLogico):
    def __init__(self,cantidad):
        super().__init__("Error: Cantidad de productos sobrepasada")
        messagebox.showerror("Error", f"El maximo de este producto que puedes quitar es {cantidad}")
        
#Falta aplicar este
class saldoInsuficiente(ErrorLogico):
    def __init__(self):
        super().__init__("Error: El usuario no tiene saldo suficiente para comprar")
        messagebox.showerror("Error", "¡No tienes saldo suficiente!")
        
#Las sugeridas
class entrySinvalor(ErrorLogico):
    def __init__(self):
        super().__init__("Error: Debes llenar todos los campos")
        messagebox.showerror("Error", "¡Debes llenar todos los campos!")
        

class dtipoInvalido(ErrorLogico):
    def __init__(self,field,tipoV):
        super().__init__("Error: El tipo de dato ingresado no corresponde en este campo")
        messagebox.showerror("Error", f"Error en la entrada {field}: Debes ingresar {tipoV}")
