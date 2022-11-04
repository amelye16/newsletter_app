import React from 'react';
import { Layout } from '../components/commons/Layout';
import {NewsletterContextProvider} from '../contexts/newsletterContext';
import { Statistics } from '../components/statistics/Statistics'
export const AllStatistics = () => {
  return (
    <div>
      <Layout>
      <NewsletterContextProvider>
        <div className="card col-6 offset-3">
          <div className="card-header">Statistics Details </div>
          <div className="card-body">
            <Statistics/>
          </div>
        </div>  
        </NewsletterContextProvider>      
      </Layout>
    </div>
  );
};
