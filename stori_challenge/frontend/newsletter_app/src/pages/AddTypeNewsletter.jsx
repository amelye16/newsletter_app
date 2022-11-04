import React from "react";
import { Layout } from "../components/commons/Layout";
import { NewsletterContextProvider } from "../contexts/newsletterContext";
import { NewsletterType } from "../components/newsletter/NewsletterType";
export const AddTypeNewsletter = () => {
  return (
    <div>
      <Layout>
        <NewsletterContextProvider>
          <div className="card">
            <div className="card-header">
              Newsletter - Create Newsletter Type
            </div>
            <div className="card-body">
              <NewsletterType />
            </div>
          </div>
        </NewsletterContextProvider>
      </Layout>
    </div>
  );
};
