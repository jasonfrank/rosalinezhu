import logo from '../logo.svg';
import './root.css'

export default function Root({children}) {
    return (
        <>
            <header className="header">
                <div className="header-content">
                    <img src={logo} alt="Logo" className="logo" />
                    <h1 className="title">US Government Site Visits</h1>
                </div>
            </header>
            <div id="detail">
                {children}
            </div>
        </>
    );
}