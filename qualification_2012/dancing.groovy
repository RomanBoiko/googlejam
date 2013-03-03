IN  = new File('in/dancing').text
OUT = new File('out/dancing.out')
OUT.write('')

lines = IN.split("\n")
for(LN in (1 .. lines.first().toInteger() ) ) {
	vector=lines[LN].split(' ').collect{it as int}
	
	def N=vector[0]
	def S=vector[1]
	def p=vector[2]
	withoutSurprize=[]
	withSurprize=[]
	for(googler in (1 .. N)){
		res=vector[googler+2]
		two=res-p
		if(two<0) {
			continue
		}
		if( two>=( 2*(p-1) ) ) {
			withoutSurprize.add(res)
		} else {
			if( two>=( 2*(p-2) ) ) {
				withSurprize.add(res)
			}
		}
	}
	ress=withoutSurprize.size()
	ws = withSurprize.size()<=S?withSurprize.size():S
	ress+=ws
	
	OUT.append("Case #${LN}: $ress\n")
}
//	}

