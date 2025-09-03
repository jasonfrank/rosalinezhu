import logo from '../logo.svg';

export default function Root({children}) {
    return (
        <>
            <header className="header">
                <div className="logo-container">
                    <img src={logo} alt="Logo" className="logo" />
                </div>
                <h1>US Government Site Visits</h1>
            </header>
            <div id="detail">
                {children}
            </div>
        </>
    );
}