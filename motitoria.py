lista1 = [{"filmes" : ["indiana jones", "star wars", "harry potter"]}, {"precos": ["1.5", "2.5"]}]
lista2 = [{}]
lista3 = [{}]


procurando = "harry potter"

for dic in lista1:
    if("filmes" in dic):
        if(procurando in dic["filmes"]):
            print(dic["filmes"].index(procurando))
            