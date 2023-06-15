from Electronico import Electronico


class Tv (Electronico):
    def __init__(self, nombre, marca, pulgadas, resolucion, precio, cantidad, supermercado):
        super(Tv, self).__init__(nombre, marca, precio, cantidad, supermercado)
        self.pulgadas = pulgadas
        self.resolucion = resolucion