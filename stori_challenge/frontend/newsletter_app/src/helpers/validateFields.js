
export const validateFields = ({ newsletterTitle, newsletterDescription,newsletterType},file ) => {
  if (newsletterTitle === "") return "Title is required";

  if (newsletterDescription === "") return "Description is required";

   if (newsletterType < 1 || typeof newsletterType === "undefined")
    return "You need to provide a Type for newsletter";
  
        
 if (file === null || typeof file === "undefined")
  return "You need to provide a file";
  
  if (file.type!== "application/pdf" && file.type !== "image/png") return "We only support .pdf / .png";

  if (file.size > 2000000) return "The file is too large";

  return "";
};
