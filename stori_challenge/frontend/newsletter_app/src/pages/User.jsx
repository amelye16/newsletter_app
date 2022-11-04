import React from 'react';
import { Header } from '../components/commons/Header';
import { NewsletterContextProvider } from '../contexts/newsletterContext';
import { Unsuscribed } from '../components/user/Unsuscribed';

export const User = () => {
  return (
    <div>
      <Header isUser={1} />
      <NewsletterContextProvider>
        <div className="container">
          <div className="row">
            <div className="col-8 offset-2">
              <Unsuscribed />
            </div>
          </div>
        </div>
      </NewsletterContextProvider>
    </div>
  );
};
