import React from 'react';
import { Moon, Sun, User, Settings } from 'lucide-react';
import { Link, useLocation } from 'react-router-dom';
import { useTheme } from '../../context/ThemeContext';
import { useAuth } from '../../context/AuthContext';

export function Navbar() {
    const location = useLocation();
    const { theme, toggleTheme } = useTheme();
    const { user } = useAuth();

    return (
        <nav className="navbar-nav ms-auto d-flex align-items-center gap-3 mt-3 mt-md-0">
            <Link
                to="/recipes"
                className={`nav-link px-2 ${location.pathname === '/recipes' ? 'active fw-semibold' : ''}`}
            >
                Recipes
            </Link>

            <div className="vr mx-2 d-none d-md-block"></div>

            <div className="d-flex align-items-center gap-3">
                <button
                    onClick={toggleTheme}
                    className="btn btn-link nav-link p-1"
                    aria-label="Toggle Theme"
                >
                    {theme === 'light' ? <Moon size={20} /> : <Sun size={20} />}
                </button>

                {user ? (
                    <>
                        <Link to="/profile" className="btn btn-link nav-link p-1" aria-label="Profile">
                            <User size={20} />
                        </Link>

                        <Link to="/settings" className="btn btn-link nav-link p-1" aria-label="Settings">
                            <Settings size={20} />
                        </Link>
                    </>
                ) : (
                    <>
                        <Link to="/login" className="btn btn-link nav-link text-decoration-none">
                            Login
                        </Link>

                        <Link to="/register" className="btn btn-primary btn-sm">
                            Register
                        </Link>
                    </>
                )}
            </div>
        </nav>
    );
}
