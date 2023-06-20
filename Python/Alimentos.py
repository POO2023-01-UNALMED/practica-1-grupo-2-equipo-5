class Alimentos():
    
    def __init__(self,*args):
        if len(args)==2:
            self.nombre=args[0].nombre
            self.precio=args[0].precio
            self.cantidad=args[1]
            self.supermercado=args[0].supermercado
            
        else:
            self.nombre = args[0]
            self.precio = args[1]
            self.cantidad = args[2]
            self.supermercado = args[3]
