class Electronico():
    def __init__(self,*args):
        if len(args)==2:
            self.nombre=args[0].nombre
            self.marca=args[0].marca
            self.precio=args[0].precio
            self.cantidad=args[1]
            self.supermercado=args[0].supermercado
        else:
            self.nombre=args[0]
            self.marca=args[1]
            self.precio=args[2]
            self.cantidad=args[3]
            self.supermercado=args[4]