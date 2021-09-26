
mon_dict={"Christophe":"papou","Astrid":"mamou","Jump":"jumpy","Erri" :"erri"}
mon_dict #{"Christophe":"papou","Astrid":"mamou","Jump":"jumpy","Erri" :"erri"}
mon_dict["Astrid"] #'mamou'
mon_dict["astrid"] # KeyError: 'astrid'
mon_dict["astrid"]="mamou"
mon_dict #{'Christophe': 'papou', 'Astrid': 'mamou', 'Jump': 'jumpy', 'Erri': 'erri', 'astrid': 'mamou'}
mon_dict["astrid"] # 'mamou'
del(mon_dict["astrid"])
mon_dict # {'Christophe': 'papou', 'Astrid': 'mamou', 'Jump': 'jumpy', 'Erri': 'erri'}
mon_dict["astrid"] # A nouveau KeyError: 'astrid'
mon_dict["Christophe"]="prof info"
mon_dict # {'Christophe': 'prof info', 'Astrid': 'mamou', 'Jump': 'jumpy', 'Erri': 'erri'}