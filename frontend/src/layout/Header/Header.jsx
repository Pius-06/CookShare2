import React from 'react';
import { ChefHat } from 'lucide-react';
import { Link, useLocation } from 'react-router-dom';
import { Navbar } from './Navbar';

export function Header() {
    const location = useLocation();
    const [isMenuOpen, setIsMenuOpen] = React.useState(false);

    // Close menu when route changes
    React.useEffect(() => {
        setIsMenuOpen(false);
    }, [location]);

    return (
        <header className="navbar navbar-expand-md navbar-light bg-body-tertiary border-bottom sticky-top">
            <div className="container">
                <Link to="/" className="navbar-brand d-flex align-items-center gap-2 text-primary fw-bold">
                    <ChefHat size={32} />
                    <span>CookShare</span>
                </Link>

                <button
                    className="navbar-toggler border-0"
                    type="button"
                    onClick={() => setIsMenuOpen(!isMenuOpen)}
                    aria-controls="navbarNav"
                    aria-expanded={isMenuOpen}
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className={`collapse navbar-collapse ${isMenuOpen ? 'show' : ''}`} id="navbarNav">
                    <Navbar />
                </div>
            </div>
        </header>
    );
}
