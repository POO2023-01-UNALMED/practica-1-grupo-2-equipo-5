from Alimentos import Alimentos

class Carne (Alimentos):
    def __init__(self, nombre, precio, supermercado, cantidad, tipo, pesolibra):
        super(Carne, self).__init__(nombre, precio, supermercado, cantidad)
        self.tipo = tipo
        self.pesolibra= pesolibra
