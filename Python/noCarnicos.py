from Alimentos import Alimentos

class noCarnicos (Alimentos):
    def __init__(self, nombre, precio, cantidad, grupo, supermercado):
        super(noCarnicos, self).__init__(nombre, precio, cantidad, supermercado)
        self.grupo = grupo