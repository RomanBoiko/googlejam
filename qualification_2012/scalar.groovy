IN  = new File('in/scalar').text
OUT = new File('out/scalar.out')
OUT.write('')
TEST = new File('out/scalar')

lines = IN.split("\n")
for(LN in (1 .. lines.first().toInteger() ) ) {
	v1=lines[LN*3].split(' ').collect{it as int}.sort()
	v2=lines[LN*3-1].split(' ').collect{it as int}.sort().reverse()
	res=0
	for ( i in 0..(v1.size()-1) ) {
    	res += v1[i]*v2[i]
	}
	OUT.append("Case #${LN}: $res\n")
}

