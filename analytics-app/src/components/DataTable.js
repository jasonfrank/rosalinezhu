import React from 'react';
import './DataTable.css';

const DataTable = ({
    data,
    title,
    className = '',
    showNote = false,
    noteText = '',
    loading = false,
    emptyMessage = 'No data available',
    dataType = 'pages',
    // Pagination props
    pagination = false,
    currentPage = 0,
    totalPages = 0,
    totalElements = 0,
    pageSize = 15,
    onPageChange = () => {}
}) => {
    if (loading) {
        return (
            <div className={`data-table-container ${className}`}>
                {title && <h2 className="data-table-title">{title}</h2>}
                <div className="data-table-loading">Loading...</div>
            </div>
        );
    }

    // Handle paginated API response format
    let tableData = data;
    if (pagination && data && data.content) {
        tableData = data.content;
    }

    if (!tableData || tableData.length === 0) {
        return (
            <div className={`data-table-container ${className}`}>
                {title && <h2 className="data-table-title">{title}</h2>}
                <div className="data-table-empty">{emptyMessage}</div>
            </div>
        );
    }

    // Check if data is API format (array of objects) or table format (array of arrays)
    const isApiFormat = tableData.length > 0 && typeof tableData[0] === 'object' && !Array.isArray(tableData[0]);
    
    let headers, rows;
    
    if (isApiFormat) {
        // Handle API data format
        if (dataType === 'pages') {
            headers = ["Domain", "Page Path", "Page Views", "Estimated Visits"];
            rows = tableData.map(item => [
                item.domain,
                item.pagePath,
                item.pageViews?.toLocaleString() || item.pageViews,
                Math.round(item.estimatedVisits || 0).toLocaleString()
            ]);
        } else if (dataType === 'domains') {
            headers = ["Domain", "Total Page Views", "Estimated Visits"];
            rows = tableData.map(item => [
                item.domain,
                item.totalPageviews?.toLocaleString() || item.totalPageviews,
                Math.round(item.estimatedVisits || 0).toLocaleString()
            ]);
        }
    } else {
        // Handle table format (legacy support)
        headers = tableData[0];
        rows = tableData.slice(1);
    }

    const startRecord = currentPage * pageSize + 1;
    const endRecord = Math.min((currentPage + 1) * pageSize, totalElements);

    return (
        <div className={`data-table-container ${className}`}>
            {title && <h2 className="data-table-title">{title}</h2>}

            {pagination && (
                <div className="pagination-info">
                    Showing {startRecord}-{endRecord} of {totalElements} results
                </div>
            )}

            <table className="data-table">
                <thead>
                    <tr>
                        {headers.map((header, index) => (
                            <th key={index}>
                                {header}
                            </th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    {rows.map((row, rowIndex) => (
                        <tr key={rowIndex}>
                            {row.map((cell, cellIndex) => (
                                <td key={cellIndex}>
                                    {cell}
                                </td>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>

            {pagination && totalPages > 1 && (
                <div className="pagination-controls">
                    <button 
                        onClick={() => onPageChange(currentPage - 1)}
                        disabled={currentPage === 0}
                        className="pagination-btn"
                    >
                        Previous
                    </button>
                    
                    <span className="pagination-current">
                        Page {currentPage + 1} of {totalPages}
                    </span>
                    
                    <button 
                        onClick={() => onPageChange(currentPage + 1)}
                        disabled={currentPage >= totalPages - 1}
                        className="pagination-btn"
                    >
                        Next
                    </button>
                </div>
            )}

            {showNote && noteText && (
                <div className="data-table-note">
                    <strong>Note:</strong> {noteText}
                </div>
            )}
        </div>
    );
};

export default DataTable;