import React from 'react';
import { useTheme } from '../context/ThemeContext';
import { Moon, Sun } from 'lucide-react';

export function Settings() {
    const { theme, toggleTheme } = useTheme();

    return (
        <div className="container py-5 animate-fade-in" style={{ maxWidth: '600px' }}>
            <h2 className="fw-bold mb-4">Settings</h2>

            <div className="card shadow-sm border-0">
                <div className="card-header border-bottom py-3">
                    <h5 className="mb-0 fw-bold">Preferences</h5>
                </div>
                <div className="card-body">
                    <div className="d-flex align-items-center justify-content-between py-2">
                        <div>
                            <div className="fw-semibold d-flex align-items-center gap-2">
                                {theme === 'dark' ? <Moon size={18} /> : <Sun size={18} />}
                                Dark Mode
                            </div>
                            <div className="text-muted small">Switch between light and dark themes</div>
                        </div>
                        <div className="form-check form-switch">
                            <input
                                className="form-check-input"
                                type="checkbox"
                                role="switch"
                                checked={theme === 'dark'}
                                onChange={toggleTheme}
                            />
                        </div>
                    </div>

                    <hr className="my-3 text-muted opacity-25" />

                    <div className="text-center text-muted small py-2">
                        More settings coming soon...
                    </div>
                </div>
            </div>
        </div>
    );
}
