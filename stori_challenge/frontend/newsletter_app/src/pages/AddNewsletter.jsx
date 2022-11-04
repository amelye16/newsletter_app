import React from "react";
import { Layout } from "../components/commons/Layout";
import {NewsletterContextProvider} from '../contexts/newsletterContext';
import { Newsletter } from "../components/newsletter/Newsletter";
export const AddNewsletter = () => {
  return (
    <div>
      <Layout>
      <NewsletterContextProvider>
        <div className="card">
          <div className="card-header"> Newsletter - Create Newsletter </div>
          <div className="card-body">
            <Newsletter/>
          </div>
        </div>  
        </NewsletterContextProvider>      
      </Layout>
    </div>
  );
};
