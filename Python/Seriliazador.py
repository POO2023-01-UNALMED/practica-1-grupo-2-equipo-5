import pickle as pk

#Creamos una clase que se encargue de la serialización de los objetos
class Serializador:

    #Creamos un método serializador
    @classmethod
    def serializarObjets(cls, listaObjetos):
        #Creamos el archivo pickle
        file = open("datos.pkl", "wb")

        #Serializamos los objetos creados
        pk.dump(listaObjetos, file)

        #Cerramos el archibo
        file.close()