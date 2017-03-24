# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

read N
declare -a strengths

for (( i=0; i<N; i++ )); do
    read strengths["$i"]
done

#sorted=(${strengths[*]})

declare -i index=${#strengths[@]}-1
#declare -i index=${#sorted[@]}-1
IFS=$'\n' sorted=($(sort -n <<<"${strengths[*]}"))


((minimal_difference=sorted["$index"]-sorted[((--index))]))
#((index-=1))

while (( "$index" > 0 )) && (( "$minimal_difference" > 0))
do
    ((difference=sorted["$index"]-sorted[((--index))]))
    (( "$difference" < "$minimal_difference" )) && minimal_difference="$difference"
done

echo "$minimal_difference"
