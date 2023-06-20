import pickle as pk
#Creamos una clase deserializador

class Deserializador():
    @classmethod
    def deserializarObjetos(cls):
        #Abrimos archivo
        file = open("datos.pkl", "rb")
        #Guardamos la lista de objetos
        listaObjetos = pk.load(file)

        file.close()
        #Retornamos la lista de objetos
        return listaObjetos