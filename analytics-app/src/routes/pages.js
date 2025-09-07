import React, { useEffect } from 'react';
import { useState } from 'react';
import axios from 'axios';
import DataTable from "../components/DataTable";
import './pages.css';

export default function Pages() {
    const [viewType, setViewType] = useState('pages'); // 'pages' or 'domains'
    const [pagePathData, setPagePathData] = useState(null);
    const [domainData, setDomainData] = useState(null);
    const [pagePathLoaded, setPagePathLoaded] = useState(false);
    const [domainLoaded, setDomainLoaded] = useState(false);
    const [error, setError] = useState(null);
    
    // Pagination state
    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize] = useState(15);

    const fetchPagePathData = async (page = 0) => {
        setError(null);

        try {
            const response = await axios.get(`http://localhost:8080/api/analytics/pages?page=${page}&size=${pageSize}`);
            const apiData = response.data;
            
            setPagePathData(apiData);
            setPagePathLoaded(true);
        } catch (err) {
            console.error('Error fetching pages data:', err);
            setError('Failed to fetch pages data from server.');
        }
    };

    const fetchDomainData = async (page = 0) => {
        setError(null);

        try {
            const response = await axios.get(`http://localhost:8080/api/analytics/domain?page=${page}&size=${pageSize}`);
            const apiData = response.data;

            setDomainData(apiData);
            setDomainLoaded(true);
        } catch (err) {
            console.error('Error fetching domain data:', err);
            setError('Failed to fetch domain data from server.');
        }
    };

    const handlePageChange = (newPage) => {
        setCurrentPage(newPage);
        if (viewType === 'pages') {
            fetchPagePathData(newPage);
        } else {
            fetchDomainData(newPage);
        }
    };

    const handleViewTypeChange = (newViewType) => {
        setViewType(newViewType);
        setCurrentPage(0); // Reset to first page when switching views
    };

    useEffect(() => {
        if (viewType === 'pages' && !pagePathLoaded) {
            fetchPagePathData(0);
        } else if (viewType === 'domains' && !domainLoaded) {
            fetchDomainData(0);
        }
    }, [viewType]);
    
    const currentData = viewType === 'pages' ? pagePathData : domainData;
    const title = viewType === 'pages' ? 'Government Pages Views' : 'Government Domain Views';
    const isLoading = viewType === 'pages' ? !pagePathLoaded : !domainLoaded;
    
    return (
        <div>
            {error && <div className="error-message">{error}</div>}
            
            {/* Toggle Buttons */}
            <div className="toggle-container">
                <button 
                    onClick={() => handleViewTypeChange('pages')}
                    className={`toggle-button ${viewType === 'pages' ? 'active' : ''}`}
                >
                    Page Path
                </button>
                <button 
                    onClick={() => handleViewTypeChange('domains')}
                    className={`toggle-button ${viewType === 'domains' ? 'active' : ''}`}
                >
                    Domain
                </button>
            </div>
            
            <DataTable 
                title={title}
                data={currentData}
                dataType={viewType}
                loading={isLoading}
                pagination={true}
                currentPage={currentData?.pageable?.pageNumber || currentPage}
                totalPages={currentData?.totalPages || 0}
                totalElements={currentData?.totalElements || 0}
                pageSize={pageSize}
                onPageChange={handlePageChange}
                showNote={true}
                noteText={`Showing ${viewType} data from the last 30 days.`}
            />
        </div>
    );
}