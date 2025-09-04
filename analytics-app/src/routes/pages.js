import React from 'react';
import { useState } from 'react';
import DataTable from "../components/DataTable";
import './pages.css';

export default function Pages() {
    const [viewType, setViewType] = useState('pages'); // 'pages' or 'domains'
    
    // Mock data for pages CSV (top-10000-pages-and-screens-30-days.csv)
    const pagesData = [
        ["page_title", "domain", "pagePath", "pageviews"],
        ["National Institute of Standards and Technology | NIST", "time.gov", "/", 98638933],
        ["National Institute of Standards and Technology | NIST", "www.time.gov", "/", 93716505],
        ["NWS Radar", "radar.weather.gov", "/", 17190773],
        ["USPS.com® - USPS Tracking® Results", "tools.usps.com", "/go/trackconfirmaction_input", 15283627],
        ["Search Public Sex Offender Registries | Dru Sjodin National Sex Offender Public Website", "www.nsopw.gov", "/search-public-sex-offender-registries", 15196221]
    ];
    
    // Mock data for domains CSV (top-10000-domains-30-days.csv)
    const domainsData = [
        ["hostname", "pageviews", "visits"],
        ["tools.usps.com", 134170291, 94863978],
        ["pubmed.ncbi.nlm.nih.gov", 84037842, 29296184],
        ["ceac.state.gov", 74401100, 7439251],
        ["www.ncbi.nlm.nih.gov", 50592140, 35300488],
        ["secure.login.gov", 50245956, 20310276]
    ];
    
    const currentData = viewType === 'pages' ? pagesData : domainsData;
    const title = viewType === 'pages' ? 'Government Pages Views' : 'Government Domain Views';
    
    return (
        <div>
            {/* Toggle Buttons */}
            <div className="toggle-container">
                <button 
                    onClick={() => setViewType('pages')}
                    className={`toggle-button ${viewType === 'pages' ? 'active' : ''}`}
                >
                    Page Path
                </button>
                <button 
                    onClick={() => setViewType('domains')}
                    className={`toggle-button ${viewType === 'domains' ? 'active' : ''}`}
                >
                    Domain
                </button>
            </div>
            
            <DataTable 
                title={title}
                data={currentData}
                showNote={true}
                noteText={`Showing top ${viewType} data from the last 30 days.`}
            />
        </div>
    );
}