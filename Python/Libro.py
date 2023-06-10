class Libro():
    def __init__(self,*args):
        
        if len(args)==2:
            self.titulo=args[0].titulo
            self.autor=args[0].autor
            self.descripcion=args[0].descripcion
            self.isbn=args[0].isbn
            self.precio=args[0].precio
            self.cantidad=args[1]
            self.supermercado=args[0].supermercado
        else:
            self.titulo=args[0]
            self.autor=args[1]
            self.descripcion=args[2]
            self.isbn=args[3]
            self.precio=args[4]
            self.cantidad=args[5]
            self.supermercado=args[6]
    
