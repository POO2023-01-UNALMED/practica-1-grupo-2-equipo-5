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

    @classmethod
    def filtroMarca(cls, lista, marca):
        filtrados = []
        for cel in lista:
            if re.search(marca.lower(), cel.marca.lower()):
                filtrados.append(cel)
        return filtrados

    @classmethod
    def filtroMarcaSimilar(cls, lista, marca):
        filtrados = []
        for cel in lista:
            if re.search(".*" + marca.lower() + ".*", cel.marca.lower()):
                filtrados.append(cel)
            else:
                for word in marca.split():
                    if re.search(".*" + word.lower() + ".*", cel.marca.lower()):
                        filtrados.append(cel)
        if len(filtrados) == 0:
            return lista
        else:
            return filtrados

    @classmethod
    def filtroAlmacenamiento(cls, lista, min_value, max_value):
        filtrados = []
        if min_value > max_value:
            temp = max_value
            max_value = min_value
            min_value = temp
        for cel in lista:
            if min_value <= int(cel.almacenamiento) <= max_value:
                filtrados.append(cel)
        return filtrados

    @classmethod
    def filtroCamaras(cls, lista, min_value, max_value):
        filtrados = []
        if min_value > max_value:
            temp = max_value
            max_value = min_value
            min_value = temp
        for cel in lista:
            if min_value <= int(cel.camaras) <= max_value:
                filtrados.append(cel)
        return filtrados

    @classmethod
    def filtroBateria(cls, lista, min_value, max_value):
        filtrados = []
        if min_value > max_value:
            temp = max_value
            max_value = min_value
            min_value = temp
        for cel in lista:
            if min_value <= int(cel.bateria) <= max_value:
                filtrados.append(cel)
        return filtrados

    @classmethod
    def filtroColor(cls, lista, color):
        filtrados = []
        for cel in lista:
            if re.search(color.lower(), cel.color.lower()):
                filtrados.append(cel)
        return filtrados

    @classmethod
    def filtroColorSimilar(cls, lista, color):
        filtrados = []
        for cel in lista:
            if re.search(".*" + color.lower() + ".*", cel.color.lower()):
                filtrados.append(cel)
            else:
                for word in color.split():
                    if re.search(".*" + word.lower() + ".*", cel.color.lower()):
                        filtrados.append(cel)
        if len(filtrados) == 0:
            return lista
        else:
            return filtrados

    @classmethod
    def filtroRam(cls, lista, min_value, max_value):
        filtrados = []
        if min_value > max_value:
            temp = max_value
            max_value = min_value
            min_value = temp
        for cel in lista:
            if min_value <= int(cel.ram) <= max_value:
                filtrados.append(cel)
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

    def __str__(self):
        return f"Nombre: {self.nombre}\nMarca: {self.marca}\nAlmacenamiento: {self.almacenamiento}" \
               f"\nNumero de camaras: {self.camaras}\nBateria: {self.bateria}mAH\nColor: {self.color}" \
               f"\nRam: {self.ram}\nPrecio: {self.precio}"
