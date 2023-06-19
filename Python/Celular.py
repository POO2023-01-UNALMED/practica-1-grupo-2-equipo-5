from Electronico import Electronico
import re


class Celular(Electronico):
    def __init__(self, nombre, marca, almacenamiento, camaras, bateria, color, ram, precio, cantidad, supermercado):
        super(Celular, self).__init__(nombre, marca, precio, cantidad, supermercado)
        self.almacenamiento = almacenamiento
        self.camaras = camaras
        self.bateria = bateria
        self.color = color
        self.ram = ram

    @classmethod
    def filtroNombre(cls, lista, nombre):
        filtrados = []
        for cel in lista:
            if re.search(nombre.lower(), cel.nombre.lower()):
                filtrados.append(cel)
        return filtrados

    @classmethod
    def filtroNombreSimilar(cls, lista, nombre):
        filtrados = []
        for cel in lista:
            if re.search(".*" + nombre.lower() + ".*", cel.nombre.lower()):
                filtrados.append(cel)
            else:
                for word in nombre.split():
                    if re.search(".*" + word.lower() + ".*", cel.nombre.lower()):
                        filtrados.append(cel)
        if len(filtrados) == 0:
            return lista
        else:
            return filtrados
