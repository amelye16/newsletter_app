import React, { createContext, useReducer } from "react";
import NewsletterReducer from "../reducer/NewsletterReducer";
import {
  GET_NEWSLETTERS,
  ADD_NEWSLETTER,
  ADD_MAIL_USER,
  GET_ALL_NEWSLETTER_TYPE,
  GET_ONE_NEWSLETTER,
  GET_ALL_SUSCRIBED,
  GET_STATISTICS,
} from "../const/actionTypes";
import Swal from "sweetalert2";
import { API_BASE_URL } from "../config";

export const NewsletterContext = createContext();

export const NewsletterContextProvider = (props) => {
 
  const initialState = {
    newsletterList: [],
    newsletterTypeList: [],
    suscribedList: [],
    statisticsList: [],
    currentData: {},
    currentDataList: {},
  };

  
  const [state, dispatch] = useReducer(NewsletterReducer, initialState);

  const messageOk = {
    message: "Success",
    details: "Data saved successfully",
  };
  
  const getNewsletters = async () => {
    try {
      const url = `${API_BASE_URL}/newsletter`;
      const response = await fetch(url, {
        method: "GET",
      });
      const data = await response.json();
      dispatch({
        type: GET_NEWSLETTERS,
        payload: data,
      });
      if (!response.ok) {
        showAlert("error", data);
      }
    } catch (error) {
      errorException(error);
    }
  };
  const getAllNewsletterType = async () => {
    try {
      const url = `${API_BASE_URL}/newsletterType`;
      const response = await fetch(url, {
        method: "GET",
      });
      const data = await response.json();
      dispatch({
        type: GET_ALL_NEWSLETTER_TYPE,
        payload: data,
      });
      if (!response.ok) {
        showAlert("error", data);
      }
    } catch (error) {
      errorException(error);
    }
  };
  const getStatistics = async () => {
    try {
      const url = `${API_BASE_URL}/statistics`;
      const response = await fetch(url, {
        method: "GET",
      });
      const data = await response.json();
      dispatch({
        type: GET_STATISTICS,
        payload: data,
      });
      if (!response.ok) {
        showAlert("error", data);
      }
    } catch (error) {
      errorException(error);
    }
  };

  const getAllSuscribed = async (email) => {
    try {
      const url = `${API_BASE_URL}/newsletterUser/${email}`;
      const response = await fetch(url, {
        method: "GET",
      });
      const data = await response.json();
      dispatch({
        type: GET_ALL_SUSCRIBED,
        payload: data,
      });
      if (!response.ok) {
        showAlert("error", data);
      }
    } catch (error) {
      errorException(error);
    }
  };

  const getNewsletter = async (newsletter) => {
    try {
      dispatch({
        type: GET_ONE_NEWSLETTER,
        payload: newsletter,
      });
    } catch (error) {
      console.log(error);
    }
  };

  const addNewsletter = async (newsletter, setLoading) => {
    try {
      setLoading(true);
      const url = `${API_BASE_URL}/newsletter`;
      const response = await fetch(url, {
        method: "POST",
        body: newsletter,
      });
      const data = await response.json();
      dispatch({
        type: ADD_NEWSLETTER,
        payload: data,
      });
      if (response.ok) {
        setLoading(false);
        showAlert("success", messageOk);
      } else {
        setLoading(false);
        showAlert("error", data);
      }
    } catch (error) {
      errorException(error);
      setLoading(false);      
    }
  };

  const addEmailUser = async (paramsEmailUser, sendList, setLoading) => {
    let url = "";
    try {
      setLoading(true);

      if (sendList === 1) url = `${API_BASE_URL}/users/uploadFile`;
      else url = `${API_BASE_URL}/users/user`;

      const response = await fetch(url, {
        method: "POST",
        body: paramsEmailUser,
      });
      const data = await response.json();
      dispatch({
        type: ADD_MAIL_USER,
        payload: data,
      });
      if (response.ok) {
        setLoading(false);
        showAlert("success", messageOk);
      } else {
        setLoading(false);
        showAlert("error", data);
      }
    } catch (error) {
      errorException(error);
      setLoading(false);    
    }
  };
  const addNewsletterType = async (nesletterType) => {
    try {
      const url = `${API_BASE_URL}/newsletterType`;
      const response = await fetch(url, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: nesletterType,
      });
      const data = await response.json();
      if (response.ok) {
        showAlert("success", messageOk);
      } else {
        showAlert("error", data);
      }
    } catch (error) {
      errorException(error);
    }
  };

  const unsuscribedNewsletter = async (unsuscribedList) => {
    if (unsuscribedList.length < 0) return;

    try {
      const url = `${API_BASE_URL}/newsletterUser`;
      const response = await fetch(url, {
        method: "PUT",
        body: unsuscribedList,
      });
      if (response.ok) {
        showAlert("success", messageOk);
        window.location.reload();
      } else {
        showAlert("error", response.data);
      }
    } catch (error) {
      errorException(error);
    }
  };
  function showAlert(icon, details) {
    Swal.fire({
      icon: icon,
      title: details.message,
      text: details.details,
    });
  }
  
 const errorException = (error) => {
  const errorExcep = {
    message: "We have a problem",
    details: error.message,
  };
  showAlert("error", errorExcep);
 }

 
  return (
    <NewsletterContext.Provider
      value={{
        currentData: state.currentData,
        newsletterList: state.newsletterList,
        newsletterTypeList: state.newsletterTypeList,
        currentDataList: state.currentDataList,
        suscribedList: state.suscribedList,
        statisticsList: state.statisticsList,

        getNewsletters,
        addNewsletter,
        addNewsletterType,
        addEmailUser,
        getAllNewsletterType,
        getNewsletter,
        getAllSuscribed,
        unsuscribedNewsletter,
        getStatistics,
      }}
    >
      {props.children}
    </NewsletterContext.Provider>
  );
};
