IN  = new File('in/tongues').text
OUT = new File('out/tongues.out')
OUT.write('')

lines = IN.split("\n")
for(LN in (1 .. lines.first().toInteger() ) ) {
	
	def mapping = [:]
	mapping.put('a', 'y')
	mapping.put('b', 'h')
	mapping.put('c', 'e')
	mapping.put('d', 's')
	mapping.put('e', 'o')
	mapping.put('f', 'c')
	mapping.put('g', 'v')
	mapping.put('h', 'x')
	mapping.put('i', 'd')
	mapping.put('j', 'u')
	mapping.put('k', 'i')
	mapping.put('l', 'g')
	mapping.put('m', 'l')
	mapping.put('n', 'b')
	mapping.put('o', 'k')
	mapping.put('p', 'r')
	mapping.put('q', 'z')
	mapping.put('r', 't')
	mapping.put('s', 'n')
	mapping.put('t', 'w')
	mapping.put('u', 'j')
	mapping.put('v', 'p')
	mapping.put('w', 'f')
	mapping.put('x', 'm')
	mapping.put('y', 'a')
	mapping.put('z', 'q')
	
	res=""
	for(inChar in lines[LN].getChars()){
		translated = mapping[""+inChar]
		res+=translated==null?inChar:translated
	}
	
	OUT.append("Case #${LN}: $res\n")
}

