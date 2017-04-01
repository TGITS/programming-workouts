# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

read N
declare -a strengths

for (( i=0; i<N; i++ )); do
    read n
    strengths["$n"]=$n
done

#echo "les nombres triés:" >&2
#echo ${strengths[*]} >&2
sorted=(${strengths[*]})
#for (( i=0; i<${#sorted[*]}; i++ )); do
#    echo "i=$i => sorted[$i]=${sorted[$i]}" >&2
#done

#IFS=$'\n' sorted=($(sort -n <<<"${strengths[*]}"))
#echo "sorted array : ${sorted[*]}" >&2
declare -i length=${#sorted[@]}-1
#echo "length : $length" >&2
declare -i index=0
#echo "index : $index" >&2

((minimal_difference=sorted[$((index+1))]-sorted["$index"]))
#echo "index : $index" >&2
#echo "minimal_difference : $minimal_difference" >&2
#echo "sorted[index+1] : " sorted[$((index+1))] >&2
#echo "sorted[index] : " sorted[$index] >&2

((index+=1))
#echo "index : $index" >&2

while (( "$index" < "$length" )) && (( "$minimal_difference" > 0))
do
    ((difference=sorted[((index+1))]-sorted["$index"]))
    #echo "difference : $difference" >&2
    (( "$difference" < "$minimal_difference" )) && minimal_difference="$difference"
    #echo "minimal_difference : $minimal_difference" >&2
    ((index+=1))
done

echo "$minimal_difference"
