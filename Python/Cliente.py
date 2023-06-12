class Cliente():
    
    def __init__(self):
        self.nombre=None
        self.direccion=None
        self.saldo=None
        self.carrito=[]
        
    def __str__(self):
        return self.nombre+"\n"+self.direccion+"\n"+self.saldo