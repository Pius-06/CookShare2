import React from 'react';
import { ChefHat, Facebook, Twitter, Instagram, Mail } from 'lucide-react';
import { Link } from 'react-router-dom';

export function Footer() {
    return (
        <footer className="bg-body-tertiary border-top mt-auto py-5">
            <div className="container">
                <div className="row g-4">
                    <div className="col-lg-4 col-md-6">
                        <div className="d-flex align-items-center gap-2 mb-3 text-primary">
                            <ChefHat size={28} />
                            <span className="h4 mb-0 fw-bold">CookShare</span>
                        </div>
                        <p className="text-muted">
                            Discover, share, and enjoy the best recipes from our community.
                            CookShare brings food lovers together.
                        </p>
                    </div>

                    <div className="col-lg-2 col-md-6">
                        <h5 className="mb-3">Explore</h5>
                        <ul className="list-unstyled d-flex flex-column gap-2">
                            <li><Link to="/" className="text-decoration-none text-muted hover-primary">Home</Link></li>
                            <li><Link to="/recipes" className="text-decoration-none text-muted hover-primary">Recipes</Link></li>
                            <li><Link to="/profile" className="text-decoration-none text-muted hover-primary">Profile</Link></li>
                        </ul>
                    </div>
                    {false &&
                        (<>
                            <div className="col-lg-2 col-md-6">
                                <h5 className="mb-3">Company</h5>
                                <ul className="list-unstyled d-flex flex-column gap-2">
                                    <li><Link to="/about" className="text-decoration-none text-muted hover-primary">About Us</Link></li>
                                    <li><Link to="/careers" className="text-decoration-none text-muted hover-primary">Careers</Link></li>
                                    <li><Link to="/contact" className="text-decoration-none text-muted hover-primary">Contact</Link></li>
                                </ul>
                            </div>

                            <div className="col-lg-4 col-md-6">
                                <h5 className="mb-3">Stay Connected</h5>
                                <div className="d-flex gap-3 mb-3">
                                    <a href="#" className="btn btn-outline-secondary btn-sm rounded-circle"><Facebook size={18} /></a>
                                    <a href="#" className="btn btn-outline-secondary btn-sm rounded-circle"><Twitter size={18} /></a>
                                    <a href="#" className="btn btn-outline-secondary btn-sm rounded-circle"><Instagram size={18} /></a>
                                    <a href="#" className="btn btn-outline-secondary btn-sm rounded-circle"><Mail size={18} /></a>
                                </div>
                            </div>
                        </>)}
                </div>

                <div className="border-top mt-4 pt-4 text-center text-muted small">
                    <p className="mb-0">&copy; {new Date().getFullYear()} CookShare. All rights reserved.</p>
                </div>
            </div>
        </footer>
    );
}
