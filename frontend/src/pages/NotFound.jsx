import React from 'react';
import { Link } from 'react-router-dom';
import { ChefHat } from 'lucide-react';

export function NotFound() {
    return (
        <div className="container d-flex flex-column justify-content-center align-items-center animate-fade-in text-center" style={{ minHeight: '70vh' }}>
            <div className="text-primary mb-3 opacity-25">
                <ChefHat size={80} />
            </div>
            <h1 className="display-1 fw-bold text-primary mb-0">404</h1>
            <h2 className="h2 mb-3 fw-bold">Page Not Found</h2>
            <p className="lead text-muted mb-4">The recipe you are looking for has been eaten.</p>
            <Link to="/" className="btn btn-primary px-4 py-2">
                <ChefHat size={20} className="me-2" /> Go Home
            </Link>
        </div>
    );
}
