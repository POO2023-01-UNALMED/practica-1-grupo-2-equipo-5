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
    
    @staticmethod        
    def listaAutores(oferlibrolist):
        autores=[]
        for l in oferlibrolist:
            if not l.autor in autores:
                autores.append(l.autor)
        return autores
    
    @staticmethod
    def filtrarporAutor(oferlibrolist,autor):
        lstporautor=[]
        
        for l in oferlibrolist:
            if l.autor==autor:
                lstporautor.append(l)
        return lstporautor
    
    @staticmethod
    def filtrarporPrecio(oferlibrolist,min,maxi):
        lstporprecio=[]
        
        for l in oferlibrolist:
            if min<=l.precio<=maxi:
                lstporprecio.append(l)
        
        return lstporprecio
        
    
    def __str__(self):
        return f"Titulo: {self.titulo}\nAutor: {self.autor}\nPrecio: {self.precio}\nUnidades disponibles: {self.cantidad}"
    
