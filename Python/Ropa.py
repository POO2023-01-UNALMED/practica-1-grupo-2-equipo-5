#Autor Yiduar
class Ropa:
    def __init__(self, tallaRopa, rutaImagen, colorRopa, precioRopa, nombreRopa, cantidadRopa, mercado, generoRopa, tipoRopa, enOferta):
        self.tallaRopa = tallaRopa
        self.colorRopa = colorRopa
        self.precioRopa = precioRopa
        self.nombreRopa = nombreRopa
        self.cantidadRopa = cantidadRopa
        self.mercado = mercado
        self.generoRopa = generoRopa
        self.tipoRopa = tipoRopa
        self.enOferta = enOferta
        self.rutaImagen = rutaImagen

    def __init__(self, ropa, cantidad):
        self.__init__(ropa.getTallaRopa(), ropa.getColorRopa(), ropa.getPrecioRopa(), ropa.getNombreRopa(),
                      cantidad, ropa.getSupermercado(), ropa.getGeneroRopa(), ropa.getTipoRopa(), ropa.isEnOferta())

    def getTallaRopa(self):
        return self.tallaRopa

    def setTallaRopa(self, tallaRopa):
        self.tallaRopa = tallaRopa

    def getGeneroRopa(self):
        return self.generoRopa

    def setGeneroRopa(self, generoRopa):
        self.generoRopa = generoRopa

    def getColorRopa(self):
        return self.colorRopa

    def setColorRopa(self, colorRopa):
        self.colorRopa = colorRopa

    def getPrecioRopa(self):
        return self.precioRopa

    def setPrecioRopa(self, precioRopa):
        self.precioRopa = precioRopa

    def getNombreRopa(self):
        return self.nombreRopa

    def setNombreRopa(self, nombreRopa):
        self.nombreRopa = nombreRopa

    def getCantidadRopa(self):
        return self.cantidadRopa

    def setCantidadRopa(self, cantidadRopa):
        self.cantidadRopa = cantidadRopa

    def getSupermercado(self):
        return self.mercado

    def setSupermercado(self, supermercado):
        self.mercado = supermercado

    def getTipoRopa(self):
        return self.tipoRopa

    def setTipoRopa(self, tipoRopa):
        self.tipoRopa = tipoRopa

    def isEnOferta(self):
        return self.enOferta

    def setEnOferta(self, enOferta):
        self.enOferta = enOferta

    @staticmethod
    def filtrarPrendas(prendas, talla, color, genero, tipo, enOferta):
        filtradas = []
        for prenda in prendas:
            if prenda.getTallaRopa().lower() == talla.lower() and \
                    prenda.getColorRopa().lower() == color.lower() and \
                    prenda.getGeneroRopa().lower() == genero.lower() and \
                    prenda.getTipoRopa().lower() == tipo.lower() and \
                    prenda.isEnOferta() == enOferta:
                filtradas.append(prenda)
        return filtradas

    def compareTo(self, o):
        if self.getNombreRopa() == o.getNombreRopa():
            return 1
        else:
            return 0

