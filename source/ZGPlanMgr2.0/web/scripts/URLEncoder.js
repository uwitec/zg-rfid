function URLEncoder(){
    if(typeof(URLEncoder.encode) == 'undefined'){
        URLEncoder.encode    = encode;
        URLEncoder.HEX_CHARS = new Array('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F');
        
        execScript("Function asciiCode(c) : asciiCode = Asc(c) : End Function","VBScript");
    }

    /** encode string to application/x-www-form-urlencoded MIME format */
    function encode(string){
        var returnValue = "";
        if(null != string && string != ""){
            for(var i=0;i<string.length;i++){
                var char = string.charAt(i);
                var code = asciiCode(char);
                if(isRemainSame(code)){
                    returnValue += char;
                }else if(char == ' '){
                    returnValue += "+";
                }else{
                    returnValue += toUTF8(char);
                }
            }
        }   
        return returnValue;
    }
    
    function isRemainSame(charCode){
        //0-9
        if(charCode >= 48 && charCode <= 57){
            return true;
        }
        //a-z A-Z
        if((charCode >= 65 && charCode <= 90) || (charCode >= 97 && charCode <= 122)){
            return true;
        }
        //".", "-", "*", and "_" 
        if(charCode == 46 || charCode == 45 || charCode == 42 || charCode == 95){
            return true;
        }       
        return false;
    }
        
    function toUTF8(szInput)
    {
        var x,uch="",szRet="";
    
        for (x=0; x<szInput.length; x++)
        {
            var wch =szInput.charCodeAt(x);
            if (!(wch & 0xFF80))
            {
                szRet += "%" + wch.toString(16);
            }
            else if (!(wch & 0xF000))
            {
                uch = "%" + (wch>>6 | 0xC0).toString(16) +
                      "%" + (wch & 0x3F | 0x80).toString(16);
                szRet += uch;
            }
            else
            {
                uch = "%" + (wch >> 12 | 0xE0).toString(16) +
                      "%" + (((wch >> 6) & 0x3F) | 0x80).toString(16) +
                      "%" + (wch & 0x3F | 0x80).toString(16);
                szRet += uch;
            }
        }
        return(szRet);
    }  
}
//force to load this class
new URLEncoder();