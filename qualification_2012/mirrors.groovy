IN  = new File('in/mirrors').text
OUT = new File('out/mirrors.out')
OUT.write('')

lines = IN.split("\n")
def nextTestCaseLine = 1
for(TC in (1 .. lines.first().toInteger() ) ) {
	vector=lines[nextTestCaseLine].split(' ').collect{it as int}
	height=vector[0]
	width =vector[1]
	dist  =vector[2]
	nextTestCaseLine=nextTestCaseLine+height+1
	println nextTestCaseLine
	

/*	
	for(inChar in lines[LN].getChars()){
		translated = mapping[""+inChar]
		res+=translated==null?inChar:translated
	}
*/	
	OUT.append("Case #${TC}: ${height}\n")
}

