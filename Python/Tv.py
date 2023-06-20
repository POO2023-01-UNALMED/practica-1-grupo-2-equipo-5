import re

from Electronico import Electronico


class Tv(Electronico):
    def __init__(self, nombre, marca, pulgadas, resolucion, precio, cantidad, supermercado):
        super(Tv, self).__init__(nombre, marca, precio, cantidad, supermercado)
        self.pulgadas = pulgadas
        self.resolucion = resolucion

    @classmethod
    def filtroNombre(cls, lista, nombre):
        filtrados = []
        for tv in lista:
            if re.search(nombre.lower(), tv.nombre.lower()):
                filtrados.append(tv)
        return filtrados

    @classmethod
    def filtroNombreSimilar(cls, lista, nombre):
        filtrados = []
        for tv in lista:
            if re.search(".*" + nombre.lower() + ".*", tv.nombre.lower()):
                filtrados.append(tv)
            else:
                for word in nombre.split():
                    if re.search(".*" + word.lower() + ".*", tv.nombre.lower()):
                        filtrados.append(tv)
        if len(filtrados) == 0:
            return lista
        else:
            return filtrados

    @classmethod
    def filtroMarca(cls, lista, marca):
        filtrados = []
        for tv in lista:
            if re.search(marca.lower(), tv.marca.lower()):
                filtrados.append(tv)
        return filtrados

    @classmethod
    def filtroMarcaSimilar(cls, lista, marca):
        filtrados = []
        for tv in lista:
            if re.search(".*" + marca.lower() + ".*", tv.marca.lower()):
                filtrados.append(tv)
            else:
                for word in marca.split():
                    if re.search(".*" + word.lower() + ".*", tv.marca.lower()):
                        filtrados.append(tv)
        if len(filtrados) == 0:
            return lista
        else:
            return filtrados

    @classmethod
    def filtroPulgadas(cls, lista, min_value, max_value):
        filtrados = []
        if min_value > max_value:
            temp = max_value
            max_value = min_value
            min_value = temp
        for tv in lista:
            if min_value <= int(tv.pulgadas) <= max_value:
                filtrados.append(tv)
        return filtrados

    @classmethod
    def filtroResolucion(cls, lista, resolucion):
        filtrados = []
        for tv in lista:
            if re.search(resolucion.lower(), tv.resolucion.lower()):
                filtrados.append(tv)
        return filtrados

    @classmethod
    def filtroResolucionSimilar(cls, lista, resolucion):
        filtrados = []
        for tv in lista:
            if re.search(".*" + resolucion.lower() + ".*", tv.resolucion.lower()):
                filtrados.append(tv)
            else:
                for word in resolucion.split():
                    if re.search(".*" + word.lower() + ".*", tv.resolucion.lower()):
                        filtrados.append(tv)
        if len(filtrados) == 0:
            return lista
        else:
            return filtrados

    @classmethod
    def filtroPrecio(cls, lista, min_value, max_value):
        filtrados = []
        if min_value > max_value:
            temp = max_value
            max_value = min_value
            min_value = temp
        for cel in lista:
            if min_value <= int(cel.precio) <= max_value:
                filtrados.append(cel)
        return filtrados