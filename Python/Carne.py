from Alimentos import Alimentos

class Carne (Alimentos):
    def __init__(self, nombre, precio, tipo, pesolibra, cantidad, supermercado):
        super(Carne, self).__init__(nombre, precio, cantidad, supermercado)
        self.tipo = tipo
        self.pesolibra = pesolibra
