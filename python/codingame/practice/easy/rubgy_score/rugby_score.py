import sys
import math
import copy

factors = [7, 5, 3]
possible_values_by_factor = {7 : [7, 5, 3], 5 : [5, 3], 3 : [3]}

class Decomposition:
    
    def __init__(self,transformations=0,tries=0,penalities=0):
        self._transformations = transformations
        self._tries = tries + transformations
        self._penalities = penalities

    def __eq__(self, other):
        return self._transformations == other._transformations and self._tries == other._tries and self._penalities == other._penalities

    def __lt__(self,other):
        if self._tries == other._tries:
            if self._transformations == other._transformations:
                return self._penalities < other._penalities
            else:
                return self._transformations < other._transformations
        else:
            return self._tries < other._tries    
        
    def __hash__(self): 
        return hash((self._transformations, self._tries, self._penalities))

    def __str__(self):
        return "{} {} {}".format(self._tries, self._transformations, self._penalities)

    def __repr__(self):
        return "Decomposition(tries={},transformations={},penalities={})".format(self._tries, self._transformations, self._penalities)

    def sum(self):
        return self._transformations * 2 + self._tries * 5 + self._penalities * 3

    def add(self,value):
        if value not in factors:
            raise ValueError("{} is not an authorized value. You can only use a value in {}".format(value, factors))

        if value == 7:
            self._transformations += 1
            self._tries += 1
        elif value == 5:
            self._tries += 1
        elif value == 3:
            self._penalities += 1

    def substract(self, value):
        if value not in factors:
            raise ValueError("{} is not an authorized value. You can only use a value in {}".format(value, factors))

        if value == 7:
            self._transformations -= 1
            self._tries -= 1
        elif value == 5:
            self._tries -= 1
        elif value == 3:
            self._penalities -= 1

def decompose(score):
    # print("Initial score {}".format(score), file=sys.stderr)
    decompositions = _decompose(score, factors, [], None)
    # print("Global decompositions {}".format(decompositions), file=sys.stderr)
    # valid_decompositions = {decomposition for decomposition in decompositions if decomposition.sum()==score}
    # print("Valid decompositions {}".format(valid_decompositions), file=sys.stderr)
    return sorted(list(decompositions))       

def _decompose(score, possible_values, decompositions,current_value):
    print("_decompose(score={}, possible_values={}, decompositions={})".format(score, possible_values, decompositions), file=sys.stderr)
    if score == 0:
        #On a fini, cela signifie que la lise de decompositions est ok
        return decompositions

    # if score < min(possible_values):
    #     #Cela signifie que je suis sur une branche qui n'aboutira pas à une décomposition correcte
    #     #il faut que je backtracke
    #     return []

    #Si on arrive là c'est qu'on est dans le cas général
    #Est-ce que j'ai une liste vide de decompositions ou est-ce que j'ai déjà des décompositions à traiter
    new_decompositions = []
    if decompositions: #on teste si la liste est non vide, liste non vide est équivalent à True en Python
        for value in possible_values:
            new_score = score - value
            if new_score == 0:
                for decomposition in decompositions:   
                        # print("while decompositions", file=sys.stderr)
                        current_decomposition = copy.deepcopy(decomposition)
                        # print("decomposition : {}".format(decomposition), file=sys.stderr)
                        current_decomposition.add(value)
                        # print("decomposition.add({}) : {}".format(value, decomposition), file=sys.stderr)
                        # new_decompositions.append(decomposition)
                        new_decompositions += _decompose(new_score, possible_values, [current_decomposition], value)
            elif new_score > 0:
                new_possible_values = possible_values_by_factor[value]
                min_value = min(new_possible_values)
                # print("new_score={}, new_possible_values={}, min_value={}".format(new_score, new_possible_values, min_value), file=sys.stderr)
                #on ne va pas sur une branche où cela ne pourra pas aboutir à la valeur 0
                if new_score >= min_value:
                    # print("if new_score(={}) >= min_value(={})".format(new_score, min_value), file=sys.stderr)
                    for decomposition in decompositions:   
                        # print("while decompositions", file=sys.stderr)
                        current_decomposition = copy.deepcopy(decomposition)
                        # print("decomposition : {}".format(decomposition), file=sys.stderr)
                        current_decomposition.add(value)
                        # print("decomposition.add({}) : {}".format(value, decomposition), file=sys.stderr)
                        new_decompositions += _decompose(new_score, new_possible_values, [current_decomposition], value)
                # elif new_score < min_value and current_value is not None:
                #     while decompositions:   
                #         # print("while decompositions", file=sys.stderr)
                #         decomposition = decompositions.pop()
                #         # print("decomposition : {}".format(decomposition), file=sys.stderr)
                #         decomposition.substract(current_value)
                #         # print("decomposition.add({}) : {}".format(value, decomposition), file=sys.stderr)
                #         new_decompositions.append(decomposition)
    else :
        for value in possible_values:
            new_score = score - value
            if new_score == 0:
                decomposition = Decomposition()
                decomposition.add(value)
                # print("decomposition.add({}) : {}".format(value, decomposition), file=sys.stderr)
                # new_decompositions.append(decomposition)
                new_decompositions += _decompose(new_score, possible_values, [decomposition], value)
            elif new_score > 0:
                new_possible_values = possible_values_by_factor[value]
                min_value = min(new_possible_values)
                # print("new_score={}, new_possible_values={}, min_value={}".format(new_score, new_possible_values, min_value), file=sys.stderr)
                #on ne va pas sur une branche où cela ne pourra pas aboutir à la valeur 0
                if new_score >= min_value:
                    # print("if new_score(={}) >= min_value(={})".format(new_score, min_value), file=sys.stderr)
                    decomposition = Decomposition()
                    decomposition.add(value)
                    new_decompositions += _decompose(new_score, new_possible_values, [decomposition], value)
                # else:
                #     decomposition = Decomposition()
                #     new_decompositions += _decompose(score, new_possible_values, [decomposition], value)

    # print("new_decompositions : {}".format(new_decompositions), file=sys.stderr)
    return new_decompositions

if __name__ == "__main__":
    n = int(input())

    # Write an action using print
    # To debug: print("Debug messages...", file=sys.stderr)

    # a try : 5 points
    # after a try, a transformation kick is played and is worth 2 extra points if successful
    # penalty kicks and drops are worth 3 points
    print("tries transformations penalties", file=sys.stderr)
    results = decompose(n)
    print("number of results : {}".format(len(results)), file=sys.stderr)
    for result in results:
        print(str(result))
    