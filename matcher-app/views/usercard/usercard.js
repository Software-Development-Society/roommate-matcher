function roundedCorners(){
    const btn = document.getElementById("userCardBttn");
    const img = document.getElementById("userImage");
    const card = document.getElementById("userCard")

    if(btn.classList.contains("nonRoundedRightCorners")){
        btn.classList.remove("nonRoundedRightCorners")
        btn.classList.add("roundedCorners")
    }else{
        btn.classList.remove("roundedCorners")
        btn.classList.add("nonRoundedRightorners")
    }

    if (img.classList.contains("imgNonRoundedTopRightCorner")){
        img.classList.remove("imgNonRoundedTopRightCorner")
        img.classList.add("imgRoundedTopCorners")
    }else{
        img.classList.remove("imgRoundedTopCorners")
        img.classList.add("imgNonRoundedTopRightCorner")
    }
    
    if (card.classList.contains("nonRoundedRightCorners")){
        card.classList.remove("nonRoundedRightCorners")
        card.classList.add("roundedCorners")
    }else{
        card.classList.remove("roundedCorners")
        card.classList.add("nonRoundedRightCorners")
    }
     
}