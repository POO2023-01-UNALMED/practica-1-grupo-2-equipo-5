#Autor Yiduar
class Ropa:
    def __init__(self, *args):
        if len(args) == 2:
            self.tallaRopa = args[0].tallaRopa
            self.rutaImagen = args[0].rutaImagen
            self.colorRopa = args[0].colorRopa
            self.precioRopa = args[0].precioRopa
            self.nombreRopa = args[0].nombreRopa
            self.cantidadRopa = args[1]
            self.supermercado = args[0].supermercado
        else:
            self.tallaRopa = args[0]
            self.rutaImagen = args[1]
            self.colorRopa = args[2]
            self.precioRopa = args[3]
            self.nombreRopa = args[4]
            self.cantidadRopa = args[5]
            self.supermercado = args[6]



    @staticmethod
    def filtrarPrendas(prendas, talla, color, genero, tipo, enOferta):
        filtradas = []
        for prenda in prendas:
            if (
                prenda.tallaRopa.lower() == talla.lower()
                and prenda.colorRopa.lower() == color.lower()
                and prenda.generoRopa.lower() == genero.lower()
                and prenda.tipoRopa.lower() == tipo.lower()
                and prenda.enOferta == enOferta
            ):
                filtradas.append(prenda)
        return filtradas

    def __str__(self):
        return (
            f"Talla: {self.tallaRopa}\n"
            f"Color: {self.colorRopa}\n"
            f"Precio: {self.precioRopa}\n"
            f"Nombre: {self.nombreRopa}\n"
            f"Cantidad: {self.cantidadRopa}\n"
            f"Supermercado: {self.supermercado}\n"
            f"Género: {self.generoRopa}\n"
            f"Tipo: {self.tipoRopa}"
        )
