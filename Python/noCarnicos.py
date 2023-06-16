from Alimentos import Alimentos

class noCarnicos (Alimentos):
    def __init__(self, nombre, precio, supermercado, cantidad, grupo):
        super(noCarnicos, self).__init__(nombre, precio, supermercado, cantidad)
        self.grupo = grupo