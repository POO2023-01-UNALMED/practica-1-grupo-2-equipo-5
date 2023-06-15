from Electronico import Electronico


class Celular(Electronico):
    def __init__(self, nombre, marca, almacenamiento, camaras, bateria, color, ram, precio, cantidad, supermercado):
        super(Celular, self).__init__(nombre, marca, precio, cantidad, supermercado)
        self.almacenamiento = almacenamiento
        self.camaras = camaras
        self.bateria = bateria
        self.color = color
        self.ram = ram
