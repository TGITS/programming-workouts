# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

read N
declare -a strengths

function search_minimal_difference {
    declare -i index=$1
    declare -i length=$2
    declare -i minimal_difference=$3
    while (( "$index" > 0 )) && (( "$minimal_difference" > 0))
    do
        ((difference=sorted["$index"]-sorted[((--index))]))
        (( "$difference" < "$minimal_difference" )) && minimal_difference="$difference"
    done
    echo "$minimal_difference"
}

for (( i=0; i<N; i++ )); do
    #read strengths["$i"]
    read n
    strengths["$n"]=$n
done

sorted=(${strengths[*]})
echo "sorted array : ${sorted[*]}" >&2
#declare -i index=${#strengths[@]}-1
declare -i index=${#sorted[@]}-1
declare -i length=$index
#IFS=$'\n' sorted=($(sort -n <<<"${strengths[*]}"))
echo "index : $index" >&2
echo "length : $length" >&2

((minimal_difference=sorted["$index"]-sorted[((--index))]))
#((index-=1))

#while (( "$index" > 0 )) && (( "$minimal_difference" > 0))
#do
#    ((difference=sorted["$index"]-sorted[((--index))]))
#    (( "$difference" < "$minimal_difference" )) && minimal_difference="$difference"
#done

minimal_difference=`search_minimal_difference "$index" "$length" "$minimal_difference"`

echo "$minimal_difference"
