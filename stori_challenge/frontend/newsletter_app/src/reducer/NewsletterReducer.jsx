import {
  GET_NEWSLETTERS,
  ADD_NEWSLETTER,
  GET_ALL_NEWSLETTER_TYPE,
  GET_ONE_NEWSLETTER,
  GET_ALL_SUSCRIBED,
  ADD_MAIL_USER,
  GET_STATISTICS,
} from "../const/actionTypes";

export default (state, action) => {
  switch (action.type) {
    case GET_NEWSLETTERS:
      return {
        ...state,
        newsletterList: action.payload,
      };
    case GET_ALL_NEWSLETTER_TYPE:
      return {
        ...state,
        newsletterTypeList: action.payload,
      };
    case GET_STATISTICS:
      return {
        ...state,
        statisticsList: action.payload,
      };
    case GET_ONE_NEWSLETTER:
      return {
        ...state,
        currentData: action.payload,
      };
    case GET_ALL_SUSCRIBED:
      return {
        ...state,
        suscribedList: action.payload,
      };
    case ADD_NEWSLETTER:
      return {
        ...state,
        currentData: action.payload,
      };
    case ADD_MAIL_USER:
      return {
        ...state,
        newsletterList: state.newsletterList.map((newsletter) =>
          newsletter.newsletterId === action.payload.newsletterId
            ? action.payload
            : newsletter
        ),
      };
    default:
      return state;
  }
};
