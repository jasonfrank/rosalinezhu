import React from 'react';
import './DataTable.css';

const DataTable = ({
    data,
    title,
    className = '',
    showNote = false,
    noteText = '',
    loading = false,
    emptyMessage = 'No data available'
}) => {
    if (loading) {
        return (
            <div className={`data-table-container ${className}`}>
                {title && <h2 className="data-table-title">{title}</h2>}
                <div className="data-table-loading">Loading...</div>
            </div>
        );
    }

    if (!data || data.length === 0) {
        return (
            <div className={`data-table-container ${className}`}>
                {title && <h2 className="data-table-title">{title}</h2>}
                <div className="data-table-empty">{emptyMessage}</div>
            </div>
        );
    }

    //First row contains headers, rest contain data
    const headers = data[0];
    const rows = data.slice(1);

    return (
        <div className={`data-table-container ${className}`}>
            {title && <h2 className="data-table-title">{title}</h2>}

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

            {showNote && noteText && (
                <div className="data-table-note">
                    <strong>Note:</strong> {noteText}
                </div>
            )}
        </div>
    );
};

export default DataTable;