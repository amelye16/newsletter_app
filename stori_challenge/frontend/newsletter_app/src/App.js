import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Home } from "./pages/Home.jsx";
import { User } from "./pages/User.jsx";
import { NewsletterList } from "./pages/NewsletterList.jsx";
import { AddNewsletter } from "./pages/AddNewsletter.jsx";
import { AddTypeNewsletter } from "./pages/AddTypeNewsletter.jsx";
import { AllStatistics } from "./pages/AllStatistics.jsx";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/home" element={<Home />} />
        <Route path="/user/unsuscribed" element={<User />} />
        <Route path="/newsletterList" element={<NewsletterList />} />
        <Route path="/newsletter" element={<AddNewsletter />} />
        <Route path="/newsletterType" element={<AddTypeNewsletter />} />
        <Route path="/statistics" element={<AllStatistics />} />
      </Routes>
    </BrowserRouter>
  );
}
export default App;
