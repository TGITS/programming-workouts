# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

read N
declare -a strengths

for (( i=0; i<N; i++ )); do
    #read Pi
    #strengths[i]=$Pi
    read strengths[i]
done

declare -i index=${#strengths[@]}-1
#echo "final index : $index" >&2
IFS=$'\n' sorted=($(sort -n <<<"${strengths[*]}"))
#echo "Sorted : ${sorted[@]}" >&2

declare -i strength_1=sorted[index]
#echo "strength_1 : $strength_1" >&2
index=$index-1
#echo "Evolution of index : $index" >&2
declare -i strength_2=sorted[index]
#echo "strength_2 : $strength_2" >&2

declare -i minimal_difference=$strength_1-$strength_2
declare -i difference=$minimal_difference
#echo "minimal_difference : $minimal_difference" >&2

while [ $index -gt 0 ]
do
    strength_1=$strength_2
    #echo "Evolution of strength_1 : $strength_1" >&2
    index=$index-1
    #echo "Evolution of index : $index" >&2
    strength_2=sorted[index]
    #echo "Evolution of strength_2 : $strength_2" >&2
    difference=$strength_1-$strength_2
    #echo "difference : $difference" >&2
    if [ $difference -lt $minimal_difference ]
    then minimal_difference=$difference
    fi
done

echo "$minimal_difference"
