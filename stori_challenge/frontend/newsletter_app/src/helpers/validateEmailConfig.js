
export const validateEmailConfig = ({ userName, userEmail},sendList,file ) => {

    if (sendList === 1) {
        if (file === null || typeof file === "undefined")
            return "You need to provide a csv file";
        if (file.type!== "text/csv") return "We only support .csv";
  
        if (file.size > 2000000) return "The file is too large";
  
    }
    else{
        if (userName === "") return "Name are required";

        if (userEmail === "") return "Email are required";

    }
    return "";
  };
  